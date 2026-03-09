package io.mastercoding.contactmanagerapp.viewmodel

import android.provider.ContactsContract
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.mastercoding.contactmanagerapp.reposistery.ContactRepository
import io.mastercoding.contactmanagerapp.room.Contact
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository): ViewModel(), Observable {
    val contacts=repository.contacts

    private var isUpdateOrDelete=false
    private lateinit var contactToUpdateOrDelete: Contact

    //data Binding with ive data
//    @Bindable
    val inputName= MutableLiveData<String?>()

//    @Bindable
    val inputEmail= MutableLiveData<String?>()

//    @Bindable
    var saveOrUpdateButtonText= MutableLiveData<String>()

//    @Bindable
    val clearAllOrDeleteButtonText= MutableLiveData<String>()

    //ini
    init{
        saveOrUpdateButtonText.value="Save"
        clearAllOrDeleteButtonText.value="Clear All"
    }

    fun insert(contact: Contact) =viewModelScope.launch {
        repository.insert(contact)
    }

    fun delete(contact: Contact)=viewModelScope.launch {
        repository.delete(contact)

        //reseting a btn and fiels
        inputName.value=null
        inputEmail.value=null
        isUpdateOrDelete=false
        saveOrUpdateButtonText.value="save"
        clearAllOrDeleteButtonText.value="Clear All"
    }

    fun update(contact: Contact)=viewModelScope.launch {
        repository.update(contact)

        //reset btn and field
        inputName.value=null
        inputEmail.value=null
        isUpdateOrDelete=false
        saveOrUpdateButtonText.value="Save"
        clearAllOrDeleteButtonText.value="Clear All"

    }

    fun clearAll()=viewModelScope.launch {
        repository.deleteAll()
    }

    fun saveOrUpdate(){
        if(isUpdateOrDelete){
            //make update
            contactToUpdateOrDelete.contact_Name=inputName.value!!
            contactToUpdateOrDelete.contact_Email=inputEmail.value!!
            update(contactToUpdateOrDelete)
        }
        else{
            //insert new contact
            val name=inputName.value!!
            val email=inputEmail.value!!
            insert(Contact(0,name,email))

            //reset
            inputName.value=null
            inputEmail.value=null
        }
    }
    fun clearAllorDelete(){
        if (isUpdateOrDelete){
            delete(contactToUpdateOrDelete)
        }else{
            clearAll()
        }
    }

    fun initUpdateAndDelete(contact: Contact){
        inputName.value=contact.contact_Name
        inputEmail.value=contact.contact_Email
        isUpdateOrDelete=true
        contactToUpdateOrDelete=contact
        saveOrUpdateButtonText.value="Update"
        clearAllOrDeleteButtonText.value="Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }


}