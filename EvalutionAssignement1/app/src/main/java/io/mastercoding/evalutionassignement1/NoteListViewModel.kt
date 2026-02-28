package io.mastercoding.evalutionassignement1


import androidx.lifecycle.ViewModel

class NoteListViewModel : ViewModel() {
    val categories = mutableListOf<String>()
    val notes = mutableListOf<String>()
}