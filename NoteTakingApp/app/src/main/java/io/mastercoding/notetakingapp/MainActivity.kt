package io.mastercoding.notetakingapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import io.mastercoding.notetakingapp.database.NoteDatabase
import io.mastercoding.notetakingapp.databinding.ActivityMainBinding
import io.mastercoding.notetakingapp.repository.NoteRepository
import io.mastercoding.notetakingapp.viewmodel.NoteViewModel
import io.mastercoding.notetakingapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()


    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))

        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepository)
        noteViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(NoteViewModel::class.java)
    }
}