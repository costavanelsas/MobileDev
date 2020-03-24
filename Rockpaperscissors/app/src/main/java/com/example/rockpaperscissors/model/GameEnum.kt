package com.example.rockpaperscissors.model

import com.example.rockpaperscissors.R

enum class GameEnum constructor(val value: Int, val imageUrl: Int) {
    ROCK(0, R.drawable.rock),
    PAPER(1, R.drawable.paper),
    SCISSORS(2, R.drawable.scissors)
}

