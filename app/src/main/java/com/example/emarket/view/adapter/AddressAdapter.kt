package com.example.emarket.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emarket.databinding.ItemAddressBinding
import com.example.emarket.model.local.entity.Addresse

class AddressAdapter(private val addresses: List<Addresse>) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressAdapter.ViewHolder {
        val binding = ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressAdapter.ViewHolder, position: Int) {
        val address = addresses[position]
        holder.bind(address)
    }

    override fun getItemCount() = addresses.size

    inner class ViewHolder(private val binding: ItemAddressBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(address: Addresse){
            binding.tvAddressTitle.text = address.title
            binding.tvAddress.text = address.address
        }
    }
}