package com.example.listadecompras.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ItemsDao {
    @Insert
    fun insert (item: ItemEntity): Long

    @Delete
    fun delete(item: ItemEntity): Int

    @Query("SELECT * FROM items")
    fun getAll (): List<ItemEntity>
}