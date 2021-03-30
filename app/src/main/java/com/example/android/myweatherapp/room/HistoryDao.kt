package com.example.android.myweatherapp.room

import android.database.Cursor
import androidx.room.*
import com.example.android.myweatherapp.model.City
import org.w3c.dom.DOMConfiguration

@Dao
interface HistoryDao {

    @Query("SELECT * FROM HistoryEntity")
    fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE city LIKE :city")
    fun getDataByWord(city: String) : List<HistoryEntity>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity : HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

    @Query("DELETE FROM historyentity WHERE id = :id")
    fun deleteById(id : Long)

    @Query("SELECT id, city, `temp` FROM historyentity")
    fun getHistoryCursor() : Cursor

    @Query("SELECT id, city, `temp` FROM historyentity WHERE id = :id")
    fun getHistoryCursor(id : Long) : Cursor


}