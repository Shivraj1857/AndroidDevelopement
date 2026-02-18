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

class MainActivity : AppCompatActivity() {

    //Declaring of wigget
    lateinit var firstText: TextView
    lateinit var firstEdit: EditText
    lateinit var firstBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //initilize
        firstText=findViewById(R.id.textView1)
        firstEdit=findViewById(R.id.editText)
        firstBtn=findViewById(R.id.but1)


        firstBtn.setOnClickListener {
            var name=firstEdit.text.toString()
            var i= Intent(this, SecondActivity::class.java)
                i.putExtra("username",name)
            startActivity(i)
        }



    }
}