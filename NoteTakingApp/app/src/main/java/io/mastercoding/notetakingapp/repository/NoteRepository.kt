package io.mastercoding.notetakingapp.repository

import io.mastercoding.notetakingapp.database.NoteDatabase
import io.mastercoding.notetakingapp.model.Note

class NoteRepository(private val db: NoteDatabase){

//ata aplala DOA che function reposistery la link karayche

    suspend fun insertNote(note: Note)=db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note:Note)=db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note:Note)=db.getNoteDao().updateNote(note)

    fun getAllNotes()=db.getNoteDao().getAllNotes()
    fun searchNote(query: String?)=db.getNoteDao().searchNote(query)


}