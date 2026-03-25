package io.mastercoding.ecommerceapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.mastercoding.ecommerceapp.databinding.ItemCartBinding
import io.mastercoding.ecommerceapp.databinding.ItemProductBinding
import io.mastercoding.ecommerceapp.model.Product
import io.mastercoding.ecommerceapp.util.ProductAdapter.ProductViewHolder


//start from cart activity
class CartAdapter(
    private val onRemoveFromCartClick: (Product) -> Unit // callback for item removal
) : ListAdapter<Product, CartAdapter.CartViewHolder>(ProductDiffCallBack()) {


    class CartViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

            val cartItemImage = binding.cartItemImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = getItem(position)
        holder.binding.cartItemTitle.text = product.title
        holder.binding.cartItemPrice.text = product.price.toString()

        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.cartItemImage)

        holder.binding.removeCartItemButton.setOnClickListener {
            onRemoveFromCartClick(product)
        }
    }
}





