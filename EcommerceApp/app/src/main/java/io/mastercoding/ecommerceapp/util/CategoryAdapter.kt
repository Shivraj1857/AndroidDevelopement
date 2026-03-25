package io.mastercoding.ecommerceapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.mastercoding.ecommerceapp.databinding.ItemCategoryBinding
import io.mastercoding.ecommerceapp.model.Category

class CategoryAdapter(
    private val onCategoryClick: (String) ->Unit
) : ListAdapter<Category,CategoryAdapter.CategoryViewHolder>
    (CategoryDiffCallback()) {


    class CategoryViewHolder(val binding: ItemCategoryBinding)
        :RecyclerView.ViewHolder(binding.root){
            val categoryImg = binding.imageViewCategory
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)

        holder.binding.categoryName.text = category.name

        Glide.with(holder.itemView.context)
            .load(category.catImg)
            .into(holder.categoryImg)

        holder.itemView.setOnClickListener {
            onCategoryClick(category.name)
        }


    }


}