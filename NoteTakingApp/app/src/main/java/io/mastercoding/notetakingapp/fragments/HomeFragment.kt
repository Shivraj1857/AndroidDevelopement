package io.mastercoding.notetakingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.mastercoding.notetakingapp.MainActivity
import io.mastercoding.notetakingapp.R
import io.mastercoding.notetakingapp.adapter.NoteAdapter
import io.mastercoding.notetakingapp.databinding.FragmentHomeBinding
import io.mastercoding.notetakingapp.model.Note
import io.mastercoding.notetakingapp.viewmodel.NoteViewModel

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        notesViewModel=(activity as MainActivity).noteViewModel

        setUpRecyclerView()
        binding.fabAddNote.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment2_to_newNoteFragment)
        }
    }

    private fun setUpRecyclerView() {
        noteAdapter= NoteAdapter()

        binding.recyclerView.apply {
            layoutManager= StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )

            setHasFixedSize(true)
            adapter=noteAdapter
        }
        activity?.let{
            notesViewModel.getAllNotes().observe(
                viewLifecycleOwner,{
                    note->noteAdapter.differ.submitList(note)
                    updateUI(note)
                    
                }
            )
        }

    }
    private fun HomeFragment.updateUI(note: List<Note>) {
        if(note.isNotEmpty()){
          binding.cardView1.visibility=View.GONE
          binding.recyclerView.visibility= View.VISIBLE
        }else{
           binding.cardView1.visibility= View.VISIBLE
           binding.recyclerView.visibility= View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu,menu)

        val mMenuSearch=menu.findItem(R.id.menu_search).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled=false
        mMenuSearch.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        //searchNote(query)
        return false
    }

    private fun searchNote(query: String?) {
        val searchQuery="%$query"
        notesViewModel.searchNote(searchQuery).observe(
            this,{list->noteAdapter.differ.submitList(list)}
        )
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText!=null){
            searchNote(newText)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}


