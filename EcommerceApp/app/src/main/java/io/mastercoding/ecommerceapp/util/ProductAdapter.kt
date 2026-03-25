package io.mastercoding.ecommerceapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.mastercoding.ecommerceapp.databinding.ItemCategoryBinding
import io.mastercoding.ecommerceapp.databinding.ItemProductBinding
import io.mastercoding.ecommerceapp.model.Product
import io.mastercoding.ecommerceapp.util.CategoryAdapter.CategoryViewHolder

class ProductAdapter(private val onProductClick: (Product) -> Unit)
    :ListAdapter<Product, ProductAdapter.ProductViewHolder>
    (ProductDiffCallBack()) {



        class ProductViewHolder(val binding: ItemProductBinding)
            : RecyclerView.ViewHolder(binding.root) {
            val productImg = binding.productImage
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)

        holder.binding.productTitle.text = product.title
        holder.binding.productPrice.text = product.price.toString()

        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.productImg)

        holder.itemView.setOnClickListener {
            onProductClick(product)
        }
    }
}

