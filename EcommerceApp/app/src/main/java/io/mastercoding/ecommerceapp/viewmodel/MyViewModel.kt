package io.mastercoding.ecommerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.mastercoding.ecommerceapp.model.Category
import io.mastercoding.ecommerceapp.model.Product
import io.mastercoding.ecommerceapp.repo.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor
    (private val repository: Repository) :ViewModel() {

        // Repository dependency is injected into
        // MyViewModel constructor

        // Fetch Categories
        fun fetchCategories(): MutableLiveData<List<Category>>{
            return repository.fetchCategories()
        }

        // Fetch Products
        fun fetchProducts(categoryName: String):MutableLiveData<List<Product>>{
            return repository.fetchProducts(categoryName)
        }


    // Add to Cart
    fun addToCart(cartItem: Product) = viewModelScope.launch {
        repository.addToCart(cartItem)
    }

    // Get Cart Items
    fun getCartItems() : MutableLiveData<List<Product>>{
        val cartItems = MutableLiveData<List<Product>>()
        viewModelScope.launch {
            val items = repository.getCartItems()
            cartItems.postValue(items)
        }
        return cartItems
    }






    fun removeFromCart(productId: String) = viewModelScope.launch {
        repository.removeFromCart(productId)
    }

    fun clearCart() = viewModelScope.launch {
        repository.clearCart()
    }


    fun savePurchasesInFirestore(product: Product){
        repository.savePurshaseInFirestore(product)
    }


}