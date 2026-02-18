package io.mastercoding.greeting_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.intellij.lang.annotations.Language

class MainActivity : AppCompatActivity() {
    //declaring variable
    lateinit var imageView: ImageView
    lateinit var nameEditText: EditText
    lateinit var languageEditText: EditText
    lateinit var TextView: TextView
    lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initilize the views

        imageView=findViewById(R.id.imageView)
        nameEditText=findViewById(R.id.editText2)
        languageEditText=findViewById(R.id.javaorkotlinEditText)
        button=findViewById(R.id.button3)
        TextView=findViewById(R.id.textView2)

        //handling user interaction
        button.setOnClickListener {

            //name ghya
            var userName=nameEditText.text.toString()
            //language vichara
            var languageSelected=languageEditText.text.toString()
            //say hello to user
            Toast.makeText(this,"Hello $userName ", Toast.LENGTH_LONG).show()

            //Display java or kotlin logo
            if(languageSelected.equals("Java"))
            {
                imageView.setImageResource(R.drawable.java)

            }
            else{
                imageView.setImageResource(R.drawable.kotlin)
            }



        }


    }
}