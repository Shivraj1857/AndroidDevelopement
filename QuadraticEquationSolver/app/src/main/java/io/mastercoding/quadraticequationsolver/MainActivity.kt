package io.mastercoding.quadraticequationsolver

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import io.mastercoding.quadraticequationsolver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var mainBinding= DataBindingUtil.setContentView<ActivityMainBinding>(
            this
            ,R.layout.activity_main
        )

        var myEquation= MyEquation(mainBinding)

        mainBinding.equation=myEquation
    }
}