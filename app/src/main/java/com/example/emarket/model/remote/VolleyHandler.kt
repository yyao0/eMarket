package com.example.emarket.model.remote

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.currentnews.model.entity.News
import com.example.currentnews.model.entity.NewsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

object VolleyHandler {

    const val BASE_URL = "http://localhost/myshop/index.php/"
    const val End_POINT_USER_REGISTER =  "User/register"
    const val End_POINT_USER_LOGIN =  "User/auth"
    const val End_POINT_USER_LOGOUT =  "User/logout"
    const val End_POINT_USER_ADDRESS =  "User/address"
    val HEADER = hashMapOf( "Content-type" to "application/json")







}