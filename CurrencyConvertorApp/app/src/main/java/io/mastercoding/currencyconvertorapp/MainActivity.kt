package io.mastercoding.currencyconvertorapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.DialogTitle
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //declaring
    lateinit var titleTextView: TextView
    lateinit var resultTextView: TextView
    lateinit var editText: EditText
    lateinit var convertButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //initilization
        titleTextView=findViewById(R.id.textView)
        resultTextView=findViewById(R.id.resultText)
        editText=findViewById(R.id.editText)
        convertButton=findViewById(R.id.convertBTN)


        convertButton.setOnClickListener {
            var enteredUSD=editText.text.toString()
            var enteredDouble:Double=enteredUSD.toDouble()

            var euros=makeConversion(enteredDouble)

            //display conversion
            resultTextView.text= "${euros}Euros"

        }

    }
    fun makeConversion(usd: Double): Double{
        //var eros: Double=usd*0.94
        return usd*0.94

    }
}