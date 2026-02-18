package io.mastercoding.kotlin_view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var myTextView: TextView
    lateinit var myEditText: EditText
    lateinit var myButton: Button
    lateinit var myImageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //initilizing of view

        myTextView=findViewById(R.id.textView77)
        myEditText=findViewById(R.id.editText3)
        myButton=findViewById(R.id.button)
        myImageView=findViewById(R.id.myImage)

        val enterText=myEditText.text.toString()
        myButton.setOnClickListener{
            Toast.makeText(this,"Hello I am Click", Toast.LENGTH_LONG).show()
            myImageView.setImageResource(R.drawable.download)

        }


    }
}