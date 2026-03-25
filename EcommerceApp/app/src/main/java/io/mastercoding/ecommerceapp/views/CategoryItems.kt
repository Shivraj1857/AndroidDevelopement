package io.mastercoding.ecommerceapp.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.mastercoding.ecommerceapp.R
import io.mastercoding.ecommerceapp.databinding.ActivityCategoryItemsBinding
import io.mastercoding.ecommerceapp.model.Product
import io.mastercoding.ecommerceapp.util.ProductAdapter
import io.mastercoding.ecommerceapp.viewmodel.MyViewModel

// Responsible for displaying products of a selected category
@AndroidEntryPoint
class CategoryItems : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryItemsBinding
    private lateinit var productAdapter: ProductAdapter

    // getting viewmodel
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryItemsBinding
            .inflate(layoutInflater)
        setContentView(binding.root)

        // Set up recyclerview
        productAdapter = ProductAdapter { selectedProduct ->
            // Handle product click
            onProductClick(selectedProduct)
        }

        binding.recyclerViewCategory.adapter = productAdapter

        binding.recyclerViewCategory
            .layoutManager = LinearLayoutManager(this)


        // Get the category name from the intent
        var categoryName = intent
            .getStringExtra("CATEGORY_NAME") ?: ""


        // Fetch Products
        val result = viewModel.fetchProducts(categoryName)
        result.observe(this) { newProductList ->
            if (newProductList.isNotEmpty()) {
                // update products
                productAdapter.submitList(newProductList)
            }else{

                // Show error
                Log.v("TAGY", "NO PRODUCTS")
            }
        }

    }


    private fun onProductClick(selectedProduct: Product) {
        // Create an Intent to start the ProductDetails activity
        val intent = Intent(this, ProductDetails::class.java)
        intent.putExtra("productTitle", selectedProduct.title)
        intent.putExtra("productPrice", selectedProduct.price)
        intent.putExtra("productImageUrl", selectedProduct.imageUrl)
        intent.putExtra("productID",selectedProduct.id)
        startActivity(intent)
    }



}
