package io.mastercoding.viewmodelapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import io.mastercoding.viewmodelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var counterViewModel: CounterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var binding= DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        //ini the view model
        //viewmodelprovider is manage and create a instace of view moddeels
        counterViewModel= ViewModelProvider(this).get(CounterViewModel::class.java)

        //set a initial value to  counter view
       // binding.textView2.text=counterViewModel.getCurrentCounter().toString()


//        binding.btn.setOnClickListener {
//            counterViewModel.increamentCounter()
//            binding.textView2.text=counterViewModel.getCurrentCounter().toString() }
        //live data
        binding.lifecycleOwner=this
        //.xml ani class create kela tyach connection mainactivity madhun karaych asat
        binding.myViewModel=counterViewModel

    }
}