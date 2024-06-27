package com.testproject1.searchtickets.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun populateSettings(appData: AppData)

    @Upsert
    suspend fun saveSettings(appData: AppData)

    @Query("SELECT * from AppData WHERE id = 1")
    fun loadSettings(): AppData
}