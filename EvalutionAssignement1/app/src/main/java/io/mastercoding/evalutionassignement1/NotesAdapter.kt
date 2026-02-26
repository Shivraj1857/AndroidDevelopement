package io.mastercoding.evalutionassignement1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(
    private val categories: MutableList<String>,
    private val notes: MutableList<String>,
    private val onItemClick: (category: String, note: String) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteVH>() {

    inner class NoteVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vDot: View = itemView.findViewById(R.id.vDot)
        val tvNoteLine: TextView = itemView.findViewById(R.id.tvNoteLine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteVH(view)
    }

    override fun onBindViewHolder(holder: NoteVH, position: Int) {
        val cat = categories[position]
        val note = notes[position]

        holder.tvNoteLine.text = "$cat: $note"

        val ctx = holder.itemView.context
        val color = when (cat) {
            "Work" -> ContextCompat.getColor(ctx, R.color.workBlue)
            "Personal" -> ContextCompat.getColor(ctx, R.color.personalGreen)
            "Study" -> ContextCompat.getColor(ctx, R.color.studyRed)
            else -> ContextCompat.getColor(ctx, android.R.color.darker_gray)
        }
        holder.vDot.background.setTint(color)

        // click → forward the exact strings
        holder.itemView.setOnClickListener {
            onItemClick(cat, note)
        }
    }

    override fun getItemCount(): Int = notes.size

    fun add(cat: String, note: String) {
        categories.add(cat)
        notes.add(note)
        notifyItemInserted(notes.size - 1)
    }

    fun isEmpty(): Boolean = notes.isEmpty()
}