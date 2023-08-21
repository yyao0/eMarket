package com.example.emarket.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CheckoutPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val fragmentList: List<Fragment>) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) =  fragmentList[position]
}
