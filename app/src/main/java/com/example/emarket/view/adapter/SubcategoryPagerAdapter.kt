package com.example.emarket.view.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.emarket.model.local.entity.Subcategory
import com.example.emarket.view.fragment.ProductBySubcategoryFragment

class SubcategoryPagerAdapter(
    private val subcategories: List<Subcategory>,
    fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount() = subcategories.size

    override fun createFragment(position: Int): Fragment {
        val subcategory = subcategories[position]
        return ProductBySubcategoryFragment.newInstance(subcategory)
    }
}