package io.mastercoding.hiltapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//provide a dependency directly in this component that need dependency
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //ingest repo
    @Inject
    lateinit var greetingReposistory: GreetingReposistory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var greetingText: TextView=findViewById(R.id.greetingTextView)
        greetingText.setText(greetingReposistory.sayHello())
       // greetingText.text=greetingReposistory.sayHello().toString()
        //val r1= GreetingReposistory()
        //greetingText.text=r1.sayHello().toString()


    }
}