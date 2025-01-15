package com.example.adobeapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDao {

    @Insert
    suspend fun insert(groceryItem: Item)


    @Update
    suspend   fun update(item: Item)
    @Delete
    suspend fun delete(item: Item)
    @Query("SELECT * from item WHERE id = :itemId")
    fun getItem(itemId: Int): Flow<Item>
    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>
}