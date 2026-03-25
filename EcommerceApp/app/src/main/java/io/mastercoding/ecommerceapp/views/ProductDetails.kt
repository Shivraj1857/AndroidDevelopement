package io.mastercoding.ecommerceapp.views

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.mastercoding.ecommerceapp.R
import io.mastercoding.ecommerceapp.databinding.ActivityProductDetailsBinding
import io.mastercoding.ecommerceapp.model.Product
import io.mastercoding.ecommerceapp.viewmodel.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@AndroidEntryPoint
class ProductDetails : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private val viewModel: MyViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the product data from intent
        val productTitle = intent.getStringExtra("productTitle") ?: ""
        val productPrice = intent.getDoubleExtra("productPrice", 0.0)
        val productImageUrl = intent.getStringExtra("productImageUrl") ?: ""
        val productID= intent.getStringExtra("productID") ?:"0"


        // Display the product Details
        binding.productTitleDetail.text = productTitle
        binding.productPriceDetail.text = "$ $productPrice"

        // Load the product image
        Glide.with(this)
            .load(productImageUrl)
            .into(binding.productImageDetail)

        // Handle click event on Cart Button
        binding.addToCartButton.setOnClickListener{
            addToCart(Product(productID, productTitle, productPrice, productImageUrl))

        }

    }

    fun addToCart(product: Product){
        // Insert item into ROOM database
        viewModel.addToCart(product)
        Toast.makeText(this,
            "Added to Cart",
            Toast.LENGTH_SHORT).show()

    }
}