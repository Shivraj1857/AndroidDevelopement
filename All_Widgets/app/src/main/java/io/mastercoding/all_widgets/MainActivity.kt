package io.mastercoding.all_widgets

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //declare
    lateinit var radiogroup: RadioGroup
    lateinit var radio1: RadioButton
    lateinit var radio2: RadioButton
    lateinit var radio3: RadioButton


    lateinit var checkb: CheckBox
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

       //radiofun call
        displayCheckBox()
        displayRadioBtn()
        displaySpinner()
        displayDatePicker()
        displayProgres()





    }

//radio buuton
    fun displayCheckBox(){
        checkb=findViewById(R.id.checkBox)
        checkb.setOnCheckedChangeListener { _,isChecked ->

            if(isChecked)
            {
                Toast.makeText(this,"Tomato is checked", Toast.LENGTH_SHORT).show()
            }
            else{
                //kay nahi
            }
        }
    }
    fun displayRadioBtn(){
        //ini
        radiogroup=findViewById<RadioGroup>(R.id.radiogroup)
        radio1=findViewById<RadioButton>(R.id.r1)
        radio2=findViewById<RadioButton>(R.id.r3)
        radio3=findViewById<RadioButton>(R.id.r3)

        radiogroup.setOnCheckedChangeListener { radiogroup,i ->
            when(i){
                R.id.r1->{
                    Toast.makeText(this,"Cheese kha bhava", Toast.LENGTH_SHORT).show()
                }
                R.id.r2->{
                    Toast.makeText(this,"spice kha bhava", Toast.LENGTH_SHORT).show()
                }
                R.id.r3->{
                    Toast.makeText(this,"Onion kha bhava", Toast.LENGTH_SHORT).show()
                }


            }
        }

    }

    fun displaySpinner(){
        val spinner: Spinner=findViewById(R.id.mySpinner)
        //data for spinner in form array
        val operatingSystem=listOf("Linux","Windows","Android","MacOS")

        val adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,operatingSystem)
        spinner.adapter=adapter

        //Handling a item seleted
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem2=operatingSystem[position]
                Toast.makeText(applicationContext,"You selected $selectedItem2", Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }


//    fun displayTimePicker(){
//        val TimePicker: TimePicker=findViewById(R.id.timepicker)
//        //handle time chages
//        TimePicker.setOnTimeChangedListener { timePicker,hourOfDay,minute->
//            val sele=String.format("%02d:%02d",hourOfDay,minute)
//            Toast.makeText(applicationContext,"your time: $sele ", Toast.LENGTH_SHORT).show()
//
//        }
//
//    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun displayDatePicker(){
        val datepicker=findViewById<DatePicker>(R.id.datepi)
        datepicker.setOnDateChangedListener { datepicker,i,i2,i3->
            Toast.makeText(applicationContext,"Year:$i,Month:${i2+1},Day:$i3", Toast.LENGTH_SHORT).show()
        }
    }

    fun displayProgres(){
        val progress: ProgressBar=findViewById(R.id.pro)
        val value=60
        progress.progress=value
    }



}