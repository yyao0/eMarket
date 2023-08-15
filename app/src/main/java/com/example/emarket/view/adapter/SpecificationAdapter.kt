package com.example.emarket.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emarket.databinding.ItemSpecificationBinding
import com.example.emarket.model.local.entity.Specification

class SpecificationAdapter(private val specifications: List<Specification>) : RecyclerView.Adapter<SpecificationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpecificationAdapter.ViewHolder {
        val binding = ItemSpecificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpecificationAdapter.ViewHolder, position: Int) {
        val specification = specifications[position]
        holder.bind(specification)
    }

    override fun getItemCount() = specifications.size

    inner class ViewHolder(private val binding: ItemSpecificationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(specification: Specification){
            binding.tvSpecificationTitle.text = specification.title
            binding.tvSpecificationValue.text = specification.specification
        }
    }
}