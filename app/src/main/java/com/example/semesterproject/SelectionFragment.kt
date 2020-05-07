package com.example.semesterproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.selection_fragment.*

class SelectionFragment : Fragment() {
    private lateinit var tictactoeButton: Button
    private lateinit var orderchaosButton: Button
    private lateinit var hexapawnButton: Button
    private lateinit var rpsButton: Button
    private lateinit var count21Button: Button
    private lateinit var ballRollButton: Button
    private lateinit var backButton: Button
    private lateinit var placeholderButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.selection_fragment, container, false)
        activity?.title = "Select Game"

        tictactoeButton = view.findViewById(R.id.ttt_button)
        orderchaosButton = view.findViewById(R.id.orderchaosbutton)
        hexapawnButton = view.findViewById(R.id.hexapawnbutton)
        rpsButton = view.findViewById(R.id.rpsbutton)
        count21Button = view.findViewById(R.id.count21button)
        ballRollButton = view.findViewById(R.id.ballrollbutton)
        backButton = view.findViewById(R.id.backbutton)
        placeholderButton = view.findViewById(R.id.button9)

        tictactoeButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_selectionFragment_to_tictactoeFragment))
        orderchaosButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_selectionFragment_to_orderChaosFragment))
        hexapawnButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_selectionFragment_to_hexapawnFragment))
        rpsButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_selectionFragment_to_RPSFragment))
        count21Button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_selectionFragment_to_countToTwentyOne))
        ballRollButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_selectionFragment_to_ballRollFragment))
        backButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_selectionFragment_to_titleFragment))

        orderchaosButton.visibility = View.GONE
        ballRollButton.visibility = View.GONE
        placeholderButton.visibility = View.GONE
        hexapawnButton.isEnabled = false

        return view
    }
}