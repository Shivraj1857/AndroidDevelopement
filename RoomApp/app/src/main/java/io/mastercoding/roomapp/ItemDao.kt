package io.mastercoding.roomapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {

    //Insert
    @Insert
    suspend fun insertItem(item: Item)

    //Delete
    @Delete
   suspend fun deleteItem(item: Item)

    //Update
    @Update
    suspend fun updateItems(item: Item)

    //deleteAll
    @Query("DELETE FROM Item")
    fun deleteAllItems()

    //getAll
    @Query("SELECT * FROM Item")
    fun getAllItemsInDB(): LiveData<List<Item>>
}