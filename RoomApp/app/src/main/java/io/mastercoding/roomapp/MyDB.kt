package io.mastercoding.roomapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1)
abstract class MyDB: RoomDatabase() {

    //geting a DAO instance
    abstract fun itemDAO(): ItemDao

    //access me method for create or get db
    companion object{
        //singletom pattern
        @Volatile
        //thread locallychange jari kela na to purn baki thread la pan globally ssamjatay
        private var Instance: MyDB?=null

        //instance is not null then return
        //if null create null instance

        fun getDatabase(context: Context): MyDB{
            return Instance?:synchronized(this){
                Room.databaseBuilder(
                    context,
                    MyDB::class.java,
                    "item_database"
                ).allowMainThreadQueries().build()
                    .also { Instance=it }
            }
        }
    }
}