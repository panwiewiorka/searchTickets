package com.testproject1.searchtickets.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    AppData::class,
], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): AppDao
}