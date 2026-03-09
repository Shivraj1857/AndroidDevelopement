package io.mastercoding.contactmanagerapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer_Table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val contact_id: Int,
    var contact_Name: String,
    var contact_Email: String
)
