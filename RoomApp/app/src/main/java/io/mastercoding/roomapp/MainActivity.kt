package io.mastercoding.roomapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //declare of widget
    lateinit var itemName: EditText
    lateinit var itemPrice: EditText
    lateinit var itemQuantity: EditText
    lateinit var dbRecordsText: TextView
    lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //inilize a widget
        itemName=findViewById(R.id.editText1)
        itemPrice=findViewById(R.id.editText2)
        itemQuantity=findViewById(R.id.editText3)
        dbRecordsText=findViewById(R.id.finali)
        saveBtn=findViewById(R.id.saveItemBtn)

        saveBtn.setOnClickListener {
            insertItem()
        }
        displayAllRecords()




    }
    fun insertItem(){
        //text ghya edit text kadun
        val name=itemName.text.toString()
        val price=itemPrice.text.toString()
        val quantity=itemQuantity.text.toString()


        //converting price and quantity into double and int
        val intPrice=price.toDouble()
        val intQuantity=quantity.toInt()

        //instance od db
        val myDB= MyDB.getDatabase(applicationContext)

        //instance of Dao
        val myDao=myDB.itemDAO()

        //insert data in db
        var myItem: Item= Item(0,name,intPrice,intQuantity)

        CoroutineScope(Dispatchers.IO).launch {
            myDao.insertItem(myItem)
        }
    }

    fun displayAllRecords(){
        //instance od db
        val myDB= MyDB.getDatabase(applicationContext)

        //instance of Dao
        val myDao=myDB.itemDAO()

        myDao.getAllItemsInDB().observe(this,
            Observer{
                var result=""

                for(item in it){
                    result=it.joinToString("\n")
                }
                dbRecordsText.text=result
            })
    }
}