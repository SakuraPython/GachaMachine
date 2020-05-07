package com.example.semesterproject

import androidx.lifecycle.ViewModel

class GameStateViewModel: ViewModel() {
    var gameType: GameType = GameType.UNDEFINED
    var gameState: String = ""
}