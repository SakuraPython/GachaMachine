package com.example.semesterproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import kotlin.random.Random

class RPSFragment : Fragment() {
    private var AIChoice: Int = Random.nextInt(0, 2)

    private lateinit var rockButton: Button
    private lateinit var paperButton: Button
    private lateinit var scissorsButton: Button

    private val gameStateViewModel: GameStateViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.rps_fragment, container, false)
        gameStateViewModel.gameType = GameType.RPS
        gameStateViewModel.gameState = ""
        activity?.title = "Play Rock Paper Scissors"

        rockButton = view.findViewById(R.id.RockButton)
        paperButton = view.findViewById(R.id.PaperButton)
        scissorsButton = view.findViewById(R.id.ScissorsButton)

        rockButton.setOnClickListener {
            val reward = calculateReward(0)
            val resultString = "Player chose ${numberToWord(0)}, AI chose ${numberToWord(AIChoice)}"
            val action = RPSFragmentDirections.actionRPSFragmentToResultFragment(reward, resultString)
            view.findNavController().navigate(action)
        }

        paperButton.setOnClickListener {
            val reward = calculateReward(1)
            val resultString = "Player chose ${numberToWord(1)}, AI chose ${numberToWord(AIChoice)}"
            val action = RPSFragmentDirections.actionRPSFragmentToResultFragment(reward, resultString)
            view.findNavController().navigate(action)
        }

        scissorsButton.setOnClickListener {
            val reward = calculateReward(2)
            val resultString = "Player chose ${numberToWord(2)}, AI chose ${numberToWord(AIChoice)}"
            val action = RPSFragmentDirections.actionRPSFragmentToResultFragment(reward, resultString)
            view.findNavController().navigate(action)
        }

        return view
    }

    private fun calculateReward(playerChoice: Int): Int {
        return when (AIChoice) {
            0 -> when(playerChoice) {
                0 -> 1
                1 -> 3
                2 -> 0
                else -> 0
            }
            1 -> when(playerChoice) {
                0 -> 0
                1 -> 1
                2 -> 3
                else -> 0
            }
            2 -> when(playerChoice) {
                0 -> 3
                1 -> 0
                2 -> 1
                else -> 0
            }
            else -> 0
        }
    }

    private fun numberToWord(number: Int): String {
        return when(number) {
            0 -> "Rock"
            1 -> "Paper"
            2 -> "Scissors"
            else -> "Undefined"
        }
    }
}