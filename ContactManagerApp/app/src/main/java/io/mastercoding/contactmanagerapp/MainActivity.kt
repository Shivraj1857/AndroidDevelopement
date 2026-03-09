package io.mastercoding.contactmanagerapp

import android.graphics.pdf.models.ListItem
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.mastercoding.contactmanagerapp.databinding.ActivityMainBinding
import io.mastercoding.contactmanagerapp.reposistery.ContactRepository
import io.mastercoding.contactmanagerapp.room.Contact
import io.mastercoding.contactmanagerapp.room.ContactDatabase
import io.mastercoding.contactmanagerapp.view.MyRecyclerViewAdapter
import io.mastercoding.contactmanagerapp.viewmodel.ContactViewModel
import io.mastercoding.contactmanagerapp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Room Database
        val dao= ContactDatabase.getInstance(applicationContext).contactDAO
        val repository= ContactRepository(dao)
        val factory = ViewModelFactory(repository)

        //viewmodel
        contactViewModel= ViewModelProvider(this, factory)[ContactViewModel::class.java]
        binding.contactViewModel=contactViewModel

        binding.lifecycleOwner=this

        initRecyclerView()


    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        DisplayUsersList()
    }
    private fun DisplayUsersList(){
        contactViewModel.contacts.observe(this, Observer{
            binding.recyclerView.adapter= MyRecyclerViewAdapter(it,{selectedItem: Contact->listItemClicked(selectedItem)})
        })

    }
    private fun listItemClicked(seletedItem: Contact){
        Toast.makeText(this,"Selected name is ${seletedItem.contact_Name}", Toast.LENGTH_LONG).show()
        contactViewModel.initUpdateAndDelete(seletedItem)
    }
}