package com.example.rockpaperscissors.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rockpaperscissors.Converters
import java.time.LocalDateTime

@Entity(tableName = "rps_game")
@TypeConverters(Converters::class)
data class Game (
    @ColumnInfo(name = "date")
    var date: LocalDateTime,

    @ColumnInfo(name = "player_move")
    var playerMove: GameEnum,

    @ColumnInfo(name = "computer_move")
    var computerMove: GameEnum,

    @ColumnInfo(name = "result")
    var result: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)