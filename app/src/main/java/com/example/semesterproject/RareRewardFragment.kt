package com.example.semesterproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs

class RareRewardFragment : Fragment() {
    val args: RareRewardFragmentArgs by navArgs()
    private lateinit var idTextView: TextView
    private lateinit var shareButton: Button
    private lateinit var backButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.rare_reward_fragment, container, false)
        activity?.title = "Rare Reward Get!"
        idTextView = view.findViewById(R.id.item_uuid)
        shareButton = view.findViewById(R.id.share_button)
        backButton = view.findViewById(R.id.back_to_gacha)

        idTextView.text = "Item ID: ${args.uuid}"

        shareButton.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "I got rare item ${args.uuid}")
                putExtra(Intent.EXTRA_SUBJECT, "Gacha")
            }.also { intent ->
                val chooserIntent = Intent.createChooser(intent, "Send message via")
                startActivity(chooserIntent)
            }
        }

        backButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_rareRewardFragment_to_titleFragment))

        return view
    }
}