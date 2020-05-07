package com.example.semesterproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs

class ResultFragment : Fragment() {
    val args: ResultFragmentArgs by navArgs()
    private lateinit var coinTextView: TextView
    private lateinit var descTextView: TextView
    private lateinit var backButton: Button
    private val playerViewModel: PlayerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.result_fragment, container, false)
        coinTextView = view.findViewById(R.id.RewardText)
        descTextView = view.findViewById(R.id.descriptionText)
        backButton = view.findViewById(R.id.resultBackButton)
        activity?.title = "Game Results"

        val coinAmount = args.coinReward
        val desc = args.description

        if (coinAmount <= 0) {
            coinTextView.text = "Sorry, you lost :("
        } else {
            coinTextView.text = "You won ${coinAmount} coin(s)"
        }
        descTextView.text = desc

        playerViewModel.coins += coinAmount

        backButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_resultFragment_to_titleFragment))

        return view
    }
}