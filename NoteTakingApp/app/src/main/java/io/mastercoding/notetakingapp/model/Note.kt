package io.mastercoding.notetakingapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteTitle: String,
    val noteBody: String,
): Serializable



