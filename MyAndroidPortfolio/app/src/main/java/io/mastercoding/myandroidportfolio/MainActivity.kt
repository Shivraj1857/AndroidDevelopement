package io.mastercoding.myandroidportfolio

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import io.mastercoding.myandroidportfolio.adapter.AppListAdapter
import io.mastercoding.myandroidportfolio.databinding.ActivityMainBinding
import io.mastercoding.myandroidportfolio.model.AppModel
import io.mastercoding.myandroidportfolio.viewmodel.AppListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: AppListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewApps.layoutManager =
            LinearLayoutManager(this)
    }


    private fun observeData() {
        viewModel.appList.observe(this) { appList ->
            val adapter = AppListAdapter(appList) { clickedApp ->
                launchSelectedApp(clickedApp)
            }
            binding.recyclerViewApps.adapter = adapter
        }
    }



    private fun launchSelectedApp(app: AppModel) {

        val launchIntent =
            packageManager.getLaunchIntentForPackage(app.packageName)

        if (launchIntent != null) {
            startActivity(launchIntent)
        } else {
            Toast.makeText(
                this,
                "App is not installed on this device",
                Toast.LENGTH_SHORT
            ).show()
        }
    }



}

