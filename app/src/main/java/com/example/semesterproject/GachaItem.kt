package com.example.semesterproject

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class GachaItem(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var itemName: String = "Item Name",
    var rarity: Int = 0,
    var description: String = "Gacha Item"
)