package io.mastercoding.ecommerceapp.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.mastercoding.ecommerceapp.R
import io.mastercoding.ecommerceapp.databinding.ActivityCartBinding
import io.mastercoding.ecommerceapp.model.Product
import io.mastercoding.ecommerceapp.util.CartAdapter
import io.mastercoding.ecommerceapp.viewmodel.MyViewModel

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private val viewModel: MyViewModel by viewModels()
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initializing the adapter
        cartAdapter = CartAdapter(){
            // Pass the remove function to the adapter
            cartItem -> removeCartItem(cartItem)
        }


        binding.recyclerViewCart.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(this@CartActivity)
        }

        binding.clearCartButton.setOnClickListener {
            viewModel.clearCart()

            // Clear the adapter's data
            cartAdapter.submitList(emptyList())
        }
        binding.checkOutButton.setOnClickListener {
            // Handle checkout logic
            checkOutCart()
        }

    //    update gradle plugin
        // Fetch cart items
        viewModel.getCartItems().observe(this) { cartItems ->
            cartAdapter.submitList(cartItems)
        }




    }

    // Removing the cart item
    private fun removeCartItem(cartItem: Product) {

        viewModel.removeFromCart(cartItem.id)

        val updatedCartItems = viewModel.getCartItems()
            .value?.toMutableList()

        cartAdapter.submitList(updatedCartItems)


    }

    // Upload purchased items to Firestore
    private fun checkOutCart(){
        // Get the list of purchased items
        viewModel.getCartItems().observe(this){
            purchasedItems ->

            for (item in purchasedItems){
                viewModel.savePurchasesInFirestore(item)
            }
        }




    }





}