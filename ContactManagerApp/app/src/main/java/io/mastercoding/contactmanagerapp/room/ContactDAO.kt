package io.mastercoding.contactmanagerapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDAO {

    @Update
    suspend fun updateContact(contact: Contact)

    @Insert
    suspend fun insertContact(contact: Contact):Long

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("DELETE FROM Customer_Table")
    suspend fun deleteAll()

    @Query("SELECT * FROM CUSTOMER_TABLE")
    fun getAllContactInDB(): LiveData<List<Contact>>
}