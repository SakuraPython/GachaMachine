package com.example.semesterproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController

class CountToTwentyOne : Fragment() {
    private lateinit var numberView: TextView
    private lateinit var buttonOne: Button
    private lateinit var buttonTwo: Button
    private lateinit var buttonThree: Button
    private var gameNumber: Int = 0
    private var gameString: String = ""
    private val gameStateViewModel: GameStateViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.count_fragment, container, false)
        activity?.title = "Count to 21 minigame"

        if (gameStateViewModel.gameType == GameType.COUNT21) {
            gameNumber = gameStateViewModel.gameState.toInt()
        } else {
            gameStateViewModel.gameType = GameType.COUNT21
        }

        numberView = view.findViewById(R.id.game_number_view)
        buttonOne = view.findViewById(R.id.add_one_button)
        buttonTwo = view.findViewById(R.id.add_two_button)
        buttonThree = view.findViewById(R.id.add_three_button)

        buttonOne.setOnClickListener {
            gameNumber += 1
            gameString += "Added 1 (value: $gameNumber)\n"
            updateGameText()
            gameStateViewModel.gameState = "$gameNumber"
            if (gameNumber == 21) {
                gameStateViewModel.gameState = "0"
                val reward = 3
                gameString += "21 has been hit"
                val action = CountToTwentyOneDirections.actionCountToTwentyOneToResultFragment(reward, gameString)
                view.findNavController().navigate(action)
            }
            else if (gameNumber > 21) {
                gameStateViewModel.gameState = "0"
                val reward = 0
                gameString += "You overshot 21"
                val action = CountToTwentyOneDirections.actionCountToTwentyOneToResultFragment(reward, gameString)
                view.findNavController().navigate(action)
            }
        }
        buttonTwo.setOnClickListener {
            gameNumber += 2
            gameString += "Added 2 (value: $gameNumber)\n"
            updateGameText()
            gameStateViewModel.gameState = "$gameNumber"
            if (gameNumber == 21) {
                gameStateViewModel.gameState = "0"
                val reward = 3
                gameString += "21 has been hit"
                val action = CountToTwentyOneDirections.actionCountToTwentyOneToResultFragment(reward, gameString)
                view.findNavController().navigate(action)
            }
            else if (gameNumber > 21) {
                gameStateViewModel.gameState = "0"
                val reward = 0
                gameString += "You overshot 21"
                val action = CountToTwentyOneDirections.actionCountToTwentyOneToResultFragment(reward, gameString)
                view.findNavController().navigate(action)
            }
        }
        buttonThree.setOnClickListener {
            gameNumber += 3
            gameString += "Added 3 (value: $gameNumber)\n"
            updateGameText()
            gameStateViewModel.gameState = "$gameNumber"
            if (gameNumber == 21) {
                gameStateViewModel.gameState = "0"
                val reward = 3
                gameString += "21 has been hit"
                val action = CountToTwentyOneDirections.actionCountToTwentyOneToResultFragment(reward, gameString)
                view.findNavController().navigate(action)
            }
            else if (gameNumber > 21) {
                gameStateViewModel.gameState = "0"
                val reward = 0
                gameString += "You overshot 21"
                val action = CountToTwentyOneDirections.actionCountToTwentyOneToResultFragment(reward, gameString)
                view.findNavController().navigate(action)
            }
        }

        return view
    }

    private fun updateGameText() {
        numberView.text = gameNumber.toString()
    }
}