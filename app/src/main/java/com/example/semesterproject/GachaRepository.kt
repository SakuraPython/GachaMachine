package com.example.semesterproject

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.semesterproject.database.GachaDatabase
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "Gacha-Database"

class GachaRepository private constructor(context: Context){

    private val database: GachaDatabase = Room.databaseBuilder(
        context.applicationContext,
        GachaDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val gachaDao = database.gachaDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getItems(): LiveData<List<GachaItem>> = gachaDao.getItems()
    fun getItem(id: UUID): LiveData<GachaItem?> = gachaDao.getItem(id)
    //fun getRolledItems(): LiveData<List<GachaItem?>> = gachaDao.getRolledItems()
    //fun getItemsNotRolled(): LiveData<List<GachaItem?>> = gachaDao.getItemsNotRolled()

    fun updateItem(item: GachaItem) {
        executor.execute {
            gachaDao.updateItem(item)
        }
    }

    fun addItem(item: GachaItem) {
        executor.execute {
            gachaDao.addItem(item)
        }
    }

    companion object {
        private var instance: GachaRepository? = null

        fun initialize(context: Context) {
            if (instance == null) {
                instance = GachaRepository(context)
            }
        }
        fun get(): GachaRepository {
            return instance ?:
                    throw IllegalStateException("GachaRepository must be initialized")
        }
    }
}