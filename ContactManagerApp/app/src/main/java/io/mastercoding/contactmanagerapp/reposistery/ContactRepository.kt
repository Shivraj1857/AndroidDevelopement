package io.mastercoding.contactmanagerapp.reposistery

import io.mastercoding.contactmanagerapp.room.Contact
import io.mastercoding.contactmanagerapp.room.ContactDAO

//it is bridge between a data source an a view model
class ContactRepository(private val contactDao: ContactDAO) {

    val contacts=contactDao.getAllContactInDB()

    suspend fun insert(contact: Contact):Long{
        return contactDao.insertContact(contact)
    }

    suspend fun delete(contact: Contact): Unit{
        return contactDao.deleteContact(contact)
    }

    suspend fun update(contact: Contact): Unit{
        return contactDao.updateContact(contact)
    }

    suspend fun deleteAll(){
        return contactDao.deleteAll()
    }



}