package com.example.semesterproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.semesterproject.GachaItem

@Database(entities = [ GachaItem::class ], version = 1)
@TypeConverters(GachaTypeConverters::class)
abstract class GachaDatabase : RoomDatabase() {
    abstract fun gachaDao(): GachaDao
}
