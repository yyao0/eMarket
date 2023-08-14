package com.example.emarket.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emarket.databinding.ItemCategoryBinding
import com.example.emarket.model.local.entity.Subcategory
import com.squareup.picasso.Picasso

class SubcategoryAdapter(private val subcategories: List<Subcategory>, private val subcategoryClickListener: SubcategoryClickListener) : RecyclerView.Adapter<SubcategoryAdapter.ViewHolder>() {

    interface SubcategoryClickListener {
        fun onSubcategoryClick(subcategory: Subcategory)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryAdapter.ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubcategoryAdapter.ViewHolder, position: Int) {
        val subcategory = subcategories[position]
        holder.bind(subcategory)
    }

    override fun getItemCount() = subcategories.size

    inner class ViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(subcategory: Subcategory){
            binding.tvName.text = subcategory.name
            Picasso.get().load("$BASE_IMAGE_URL${subcategory.imageUrl}").into(binding.ivImage)

            binding.root.setOnClickListener {
                subcategoryClickListener.onSubcategoryClick(subcategory)

            }
        }
    }

    companion object{
        const val BASE_IMAGE_URL = "http://192.168.0.17/myshop/images/"
    }
}