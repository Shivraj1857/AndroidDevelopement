package io.mastercoding.thelotteryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    lateinit var secondTextview: TextView
    lateinit var secondedit: TextView
    lateinit var btn2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        //ini
        secondTextview=findViewById(R.id.textView2)
        secondedit=findViewById(R.id.edit2)
        btn2=findViewById(R.id.but2)

        val randomNums=generateRandomNumbers(6 )
        secondedit.text= randomNums

        //user name from main activity need create function
        val username=receivedUserName()

        //sharing generated number and user name to email app
        btn2.setOnClickListener {
            shareResult(username,randomNums)
        }




    }

    fun generateRandomNumbers(Count:Int): String{
        //random number banava
        var randomNum=List(Count){
            (0..42).random()
        }
        return randomNum.joinToString(" ")

    }

    fun receivedUserName(): String{
        var bundle:Bundle?=intent.extras
        var username= bundle?.getString("username").toString()
        return username
    }

    fun shareResult(username: String,generatedNums: String){
        //implit intent

        var i= Intent(Intent.ACTION_SEND)
        i.setType("test/plain")
        i.putExtra(Intent.EXTRA_SUBJECT,"$username generate these number")
        i.putExtra(Intent.EXTRA_TEXT,"The lottery number are :$generatedNums")
        startActivity(i)

    }
}