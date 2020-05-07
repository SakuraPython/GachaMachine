package com.example.semesterproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlin.random.Random

class TitleFragment : Fragment() {
    private lateinit var insertCoinButton: Button
    private lateinit var selectGameButton: Button
    private lateinit var coinTextView: TextView
    private lateinit var listButton: Button
    private val playerViewModel: PlayerViewModel by activityViewModels()
    private val gachaItemViewModel: GachaItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.title_fragment, container, false)

        insertCoinButton = view.findViewById(R.id.InsertCoinButton)
        selectGameButton = view.findViewById(R.id.GameSelectButton)
        coinTextView = view.findViewById(R.id.CoinCount)
        listButton = view.findViewById(R.id.list_button)
        updateText()
        activity?.title = "Main Gacha"

        insertCoinButton.setOnClickListener {
            if (playerViewModel.coins == 0) {
                Toast.makeText(context, "You have no coins, go earn some!", Toast.LENGTH_SHORT).show()
            }
            else {
                playerViewModel.coins--
                val newItem = GachaItem()
                newItem.itemName = "Generic item name"
                newItem.description = "Rolled from the main gacha"
                newItem.rarity = Random.nextInt(0, 100)
                gachaItemViewModel.saveItem(newItem)
                updateText()
                when {
                    newItem.rarity < 10 -> { //this is a rare item
                        val uuid = newItem.id.toString()
                        val action = TitleFragmentDirections.actionTitleFragmentToRareRewardFragment(uuid)
                        view.findNavController().navigate(action)
                    }
                    newItem.rarity < 40 -> Toast.makeText(context, "You got uncommon reward", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(context, "You got common reward", Toast.LENGTH_SHORT).show()
                }
            }
        }

        selectGameButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_selectionFragment))
        listButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_listGachaItem))

        return view
    }

    private fun updateText() {
        coinTextView.text = "You have ${playerViewModel.coins} coins"
    }
}