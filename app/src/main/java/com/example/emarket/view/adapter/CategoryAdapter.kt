package com.example.emarket.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emarket.databinding.ItemCategoryBinding
import com.example.emarket.model.local.entity.Category
import com.example.emarket.view.ViewConstants
import com.example.emarket.view.fragment.SubcategoryFragment
import com.squareup.picasso.Picasso

class CategoryAdapter(private val categories: List<Category>, private val categoryClickListener: CategoryClickListener) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    interface CategoryClickListener {
        fun onCategoryClick(category: Category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount() = categories.size

    inner class ViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(category: Category){
            binding.tvName.text = category.categoryName

            Picasso.get().load("${ViewConstants.BASE_IMAGE_URL}${category.categoryImageUrl}").into(binding.ivImage)
            binding.root.setOnClickListener {
                categoryClickListener.onCategoryClick(category)
            }
        }
    }
}