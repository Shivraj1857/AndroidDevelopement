package io.mastercoding.ecommerceapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import io.mastercoding.ecommerceapp.R
import io.mastercoding.ecommerceapp.model.Category
import io.mastercoding.ecommerceapp.model.Product
import io.mastercoding.ecommerceapp.room.CartDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// Centralizes data operations (Firestore & ROOM)
class Repository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val cartDao: CartDao
) {
    // Firebase: Fetch Categories from Firestore
    fun fetchCategories(): MutableLiveData<List<Category>> {

        var categoriesList = MutableLiveData<List<Category>>()

        val catImages = mapOf(
            "Electronics" to R.drawable.electronics,
            "Jewelry" to R.drawable.jewelery,
            "Men" to R.drawable.mensclothing,
            "Women" to R.drawable.womenclothing,
            "Cosmetics" to R.drawable.cosmetics,
            "Shoes" to R.drawable.runningshoes,
            "Toys" to R.drawable.toys,
            "Tools" to R.drawable.tools,
            "Home" to R.drawable.sofa,
            "Automotive" to R.drawable.brake
        )


        // Fetching data from firestore
        firestore.collection("categories")
            .get()  // retrieves data asynchronously
            .addOnSuccessListener { documents ->
                val category = documents.map{ document ->

                    // Get the image resource from the map
                    // using the document ID
                    val imageRes = catImages[document.id] ?:
                        R.drawable.ic_launcher_background

                    Category(name = document.id,
                       catImg = imageRes)
                }

                // Updating LiveData & Logging
                // postValue: Updates the value asynchronously
                // which will notify any observers (UI)
                categoriesList.postValue(category)
                Log.v("TAGY","category: $categoriesList")

            }

        return categoriesList

    }


    // Firebase: Fetch Products from Firestore
    fun fetchProducts(categoryName: String): MutableLiveData<List<Product>> {
        var productsList = MutableLiveData<List<Product>>()

        // Fetching data from firestore
        firestore.collection("categories")
            .document(categoryName)
            .collection("products")
            .get()
            .addOnSuccessListener { documents->
                val products = documents.map { document ->
                    Product(
                        id = document.id,
                        title = document.getString("title") ?: "",
                        price = document.getDouble("price") ?: 0.0 ,
                        imageUrl = document.getString("imageUrl") ?: ""
                    )
                }

                // Updating LiveData & Logging
                productsList.postValue(products)
                Log.v("TAGY","products: $productsList")
            }
            .addOnFailureListener{ exception ->
                Log.v("TAGY","Exception: $exception")
            }

        return productsList

    }



    // Room: Add Product to Cart
    suspend fun addToCart(product: Product){
        cartDao.addToCart(product)
    }


    // Room: Get Cart Items
    suspend fun getCartItems(): List<Product>{
        return cartDao.getCartItems()
    }

    // Room: Remove Product from Cart
    suspend fun removeFromCart(productId: String) {
        cartDao.removeFromCart(productId)
    }

    // Room: Clear Cart
    suspend fun clearCart() {
        cartDao.clearCart()
    }


    // Upload Product to Firestore
    fun savePurshaseInFirestore(product: Product) {

        firestore.collection("purchases")
            .add(product)
            .addOnSuccessListener {
                // Clearing the cart
                CoroutineScope(Dispatchers.IO).launch {
                    clearCart()
                }
            }

    }

}
