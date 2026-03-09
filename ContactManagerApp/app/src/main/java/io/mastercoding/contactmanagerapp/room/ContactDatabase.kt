package io.mastercoding.contactmanagerapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.mastercoding.contactmanagerapp.room.ContactDAO

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase: RoomDatabase() {

    abstract val contactDAO: ContactDAO
    companion object{
        @Volatile
        private var INSTANCE: ContactDatabase ?=null

        fun getInstance(context: Context): ContactDatabase{
            synchronized(this){
                var instace=INSTANCE
                if(instace==null){
                    //creatind db obj
                    instace= Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contacts_db"
                    ).build()
                }
                INSTANCE=instace
                return instace
            }
        }
    }
}