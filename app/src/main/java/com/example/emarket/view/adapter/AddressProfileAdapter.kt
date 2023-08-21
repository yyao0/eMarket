package com.example.emarket.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emarket.databinding.ItemAddressProfileBinding
import com.example.emarket.model.local.entity.Addresse

class AddressProfileAdapter(private var addresses: MutableList<Addresse>) : RecyclerView.Adapter<AddressProfileAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressProfileAdapter.ViewHolder {
        val binding = ItemAddressProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressProfileAdapter.ViewHolder, position: Int) {
        val address = addresses[position]
        holder.bind(address)
    }

    override fun getItemCount() = addresses.size
    fun insertAddress(address: Addresse){
        addresses.add(address)
        notifyDataSetChanged()
    }
    inner class ViewHolder(private val binding: ItemAddressProfileBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(address: Addresse){
            binding.tvAddressTitle.text = address.title
            binding.tvAddress.text = address.address
        }
    }
}