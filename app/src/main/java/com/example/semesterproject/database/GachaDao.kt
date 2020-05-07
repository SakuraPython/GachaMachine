package com.example.semesterproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.semesterproject.GachaItem
import java.util.*

@Dao
interface GachaDao {

    @Query("SELECT * FROM gachaitem")
    fun getItems(): LiveData<List<GachaItem>>

    @Query("SELECT * FROM gachaitem WHERE id=(:id)")
    fun getItem(id: UUID): LiveData<GachaItem?>

    /*@Query("SELECT * FROM gachaitem WHERE rolled=1")
    fun getRolledItems(): LiveData<List<GachaItem?>>

    @Query("SELECT * FROM gachaitem WHERE rolled=0")
    fun getItemsNotRolled(): LiveData<List<GachaItem?>>*/

    @Update
    fun updateItem(item: GachaItem)

    @Insert
    fun addItem(item: GachaItem)
}