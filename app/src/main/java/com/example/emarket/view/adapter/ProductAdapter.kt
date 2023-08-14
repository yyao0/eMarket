package com.example.emarket.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emarket.databinding.ItemProductBinding
import com.example.emarket.model.local.entity.Product
import com.squareup.picasso.Picasso

class ProductAdapter(private val products: List<Product>, private val productClickListener: ProductClickListener) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(){

    interface ProductClickListener {
        fun onProductClick(product: Product)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount() = products.size

    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.tvName.text = product.product_name
            binding.tvPrice.text = product.price
            binding.tvDescription.text = product.description
            binding.ratingBar.rating = product.average_rating.toFloat()
            Picasso.get().load("${BASE_IMAGE_URL}${product.product_image_url}").into(binding.ivImage)

            binding.root.setOnClickListener {
                productClickListener.onProductClick(product)
            }
        }
    }
    companion object{
        const val BASE_IMAGE_URL = "http://192.168.0.17/myshop/images/"
    }
}