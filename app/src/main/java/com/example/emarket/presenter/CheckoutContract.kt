package com.example.emarket.presenter

interface CheckoutContract {
    interface View {
        fun manageViewPager()
        fun getTabTitles(position: Int) : String
    }
}