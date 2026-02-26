package io.mastercoding.evalutionassignement1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.mastercoding.evalutionassignement1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Session-only lists
    private val categoriesList = mutableListOf<String>()
    private val notesList = mutableListOf<String>()

    private lateinit var adapter: NotesAdapter

    companion object {
        const val EXTRA_NOTE = "EXTRA_NOTE"
        const val EXTRA_CATEGORY = "EXTRA_CATEGORY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ✅ Initialize ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Spinner setup
        val spinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.note_categories,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        binding.spCategory.adapter = spinnerAdapter

        // ✅ RecyclerView setup (ONLY ONCE)
        adapter = NotesAdapter(categoriesList, notesList) { cat, note ->
            val intent = Intent(this, PreviewActivity::class.java)
            intent.putExtra(EXTRA_NOTE, note)
            intent.putExtra(EXTRA_CATEGORY, cat)
            startActivity(intent)
        }

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = adapter

        // ✅ Save button
        binding.btnSave.setOnClickListener {

            val noteText = binding.etNote.text?.toString()?.trim().orEmpty()
            val category = binding.spCategory.selectedItem?.toString() ?: "Work"

            if (noteText.isEmpty()) {
                Toast.makeText(this, "Please type a note first.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            adapter.add(category, noteText)
            binding.etNote.text?.clear()

            binding.btnShareAll.isEnabled = true
        }

        // ✅ Preview button
        binding.btnPreview.setOnClickListener {

            val currentNote = binding.etNote.text?.toString()?.trim().orEmpty()
            val category = binding.spCategory.selectedItem?.toString() ?: "Work"

            if (currentNote.isEmpty()) {
                Toast.makeText(this, "Type a note to preview.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, PreviewActivity::class.java)
            intent.putExtra(EXTRA_NOTE, currentNote)
            intent.putExtra(EXTRA_CATEGORY, category)
            startActivity(intent)
        }

        // ✅ Share All button
        binding.btnShareAll.setOnClickListener {

            if (adapter.itemCount == 0) {
                Toast.makeText(this, "No notes to share.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sb = StringBuilder()

            for (i in notesList.indices) {
                sb.append("${categoriesList[i]}: ${notesList[i]}")
                if (i != notesList.lastIndex) sb.append("\n")
            }

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, sb.toString())
            }

            startActivity(Intent.createChooser(shareIntent, "Share notes via"))
        }
    }
}