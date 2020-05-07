package com.example.semesterproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.selection_fragment.*

class TictactoeFragment : Fragment() {
    private lateinit var grid: List<Button>
    var player = mutableListOf('X', 'O')
    var playerMoves = mutableListOf<Int>()
    var opponentMoves = mutableListOf<Int>()
    val viewModel: GameStateViewModel by activityViewModels()
    private lateinit var backButton: Button
    private var turn = 0
    private var historyString = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tictactoe_fragment, container, false)
        viewModel.gameType = GameType.TICTACTOE
        grid = listOf(
            view.findViewById(R.id.cell_1),
            view.findViewById(R.id.cell_2),
            view.findViewById(R.id.cell_3),
            view.findViewById(R.id.cell_4),
            view.findViewById(R.id.cell_5),
            view.findViewById(R.id.cell_6),
            view.findViewById(R.id.cell_7),
            view.findViewById(R.id.cell_8),
            view.findViewById(R.id.cell_9)
        )

        for (i in grid.indices) {
            val cell = grid[i]
            cell.text = ""
            cell.setOnClickListener {
                if (cell.text == "") {
                    cell.text = player[turn % 2].toString()
                    historyString += "${player[turn % 2]} goes ${iToString(i)}\n"
                    if (turn % 2 == 0) {
                        playerMoves.add(i)
                    }
                    else {
                        opponentMoves.add(i)
                    }
                    turn++
                    val winner = checkWinner()
                    if (winner != null) {
                        val coins = when (winner) {
                            true -> 3
                            else -> 0
                        }
                        val action = TictactoeFragmentDirections.actionTictactoeFragmentToResultFragment(coins, historyString)
                        view.findNavController().navigate(action)
                    }
                }
            }
        }
        backButton = view.findViewById(R.id.ttt_back)
        backButton.visibility = View.GONE //there's actually not an action for it in the navigation graph, this is just the easiest way to make it go away without breaking the layout
        return view
    }

    fun iToString(i: Int): String {
        return when (i) {
            0 -> "top left"
            1 -> "top middle"
            2 -> "top right"
            3 -> "left center"
            4 -> "center"
            5 -> "right center"
            6 -> "bottom left"
            7 -> "bottom middle"
            8 -> "bottom right"
            else -> "idk"
        }
    }

    fun checkWinner(): Boolean? {
        if (playerMoves.containsAll(listOf(0, 1, 2))) return true
        if (playerMoves.containsAll(listOf(3, 4, 5))) return true
        if (playerMoves.containsAll(listOf(6, 7, 8))) return true
        if (playerMoves.containsAll(listOf(0, 3, 6))) return true
        if (playerMoves.containsAll(listOf(1, 4, 7))) return true
        if (playerMoves.containsAll(listOf(2, 5, 8))) return true
        if (playerMoves.containsAll(listOf(0, 4, 8))) return true
        if (playerMoves.containsAll(listOf(2, 4, 6))) return true
        if (opponentMoves.containsAll(listOf(0, 1, 2))) return false
        if (opponentMoves.containsAll(listOf(3, 4, 5))) return false
        if (opponentMoves.containsAll(listOf(6, 7, 8))) return false
        if (opponentMoves.containsAll(listOf(0, 3, 6))) return false
        if (opponentMoves.containsAll(listOf(1, 4, 7))) return false
        if (opponentMoves.containsAll(listOf(2, 5, 8))) return false
        if (opponentMoves.containsAll(listOf(0, 4, 8))) return false
        if (opponentMoves.containsAll(listOf(2, 4, 6))) return false
        val total = playerMoves.union(opponentMoves)
        if (total.containsAll(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8))) return false
        return null
    }
}