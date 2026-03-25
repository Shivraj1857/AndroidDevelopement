package io.mastercoding.ecommerceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.mastercoding.ecommerceapp.databinding.ActivityMainBinding
import io.mastercoding.ecommerceapp.util.CategoryAdapter
import io.mastercoding.ecommerceapp.viewmodel.MyViewModel
import io.mastercoding.ecommerceapp.views.CartActivity
import io.mastercoding.ecommerceapp.views.CategoryItems

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var categoryAdapter: CategoryAdapter

    // getting viewmodel
    private val viewModel: MyViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cart Button
        binding.viewCartButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        // Set up recyclerview
        categoryAdapter = CategoryAdapter { categoryName ->
            // Handle category click
            onCategoryClick(categoryName)
        }


            binding.recyclerView.adapter = categoryAdapter

            binding.recyclerView.layoutManager = GridLayoutManager(
                this,2)


            // Fetch The Categories
            val result = viewModel.fetchCategories()
            result.observe(this) { newCategoryList ->
                if (newCategoryList.isNotEmpty()) {
                    // update categories
                    categoryAdapter.submitList(newCategoryList)
                } else {
                    // show error
                    Toast.makeText(
                        this,
                        "Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }


    fun onCategoryClick(categoryName: String){
        // Handle category click
        val intent = Intent(this,
            CategoryItems::class.java)
        intent.putExtra("CATEGORY_NAME",categoryName)
        startActivity(intent)


        Toast.makeText(this,
            "Clicked: $categoryName",
            Toast.LENGTH_SHORT).show()
    }



}

