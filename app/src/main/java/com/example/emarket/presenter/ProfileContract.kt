package com.example.emarket.presenter

import com.example.emarket.model.local.entity.User

interface ProfileContract {
    interface View{
        fun displayUser(user: User)
        fun navigateToOrders()
        fun navigateToAddress()
    }
    interface Presenter{
        fun getUser(): User
    }
}