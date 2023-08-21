package com.example.emarket.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emarket.databinding.ItemProductCheckoutBinding
import com.example.emarket.model.local.entity.Item
import com.example.emarket.view.ViewConstants
import com.squareup.picasso.Picasso

class CheckoutItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<CheckoutItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckoutItemAdapter.ViewHolder {
        val binding = ItemProductCheckoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckoutItemAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemProductCheckoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item){
            binding.tvName.text = item.product_name
            binding.tvQuantity.text = item.quantity
            binding.tvAmount.text = "$ ${item.amount}"
            binding.tvUnitPrice.text = "$ ${item.unit_price}"
            Picasso.get().load("${ViewConstants.BASE_IMAGE_URL}${item.product_image_url}").into(binding.ivImage)
        }
    }
}