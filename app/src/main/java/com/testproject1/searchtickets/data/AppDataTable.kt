package com.testproject1.searchtickets.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    val departure: String = ""
)
