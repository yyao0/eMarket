package com.example.emarket.model.remote

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.emarket.model.local.entity.User
import com.example.emarket.presenter.LoginContract
import com.example.emarket.presenter.SignupContract
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

object VolleyHandler {

    const val BASE_URL = "http://192.168.0.17/myshop/index.php/"
    const val End_POINT_USER_REGISTER =  "User/register"
    const val End_POINT_USER_LOGIN =  "User/auth"
    const val End_POINT_USER_LOGOUT =  "User/logout"
    const val End_POINT_USER_ADDRESS =  "User/address"
    val HEADER = hashMapOf( "Content-type" to "application/json")

    fun userRegister(
        context: Context,
        fullName: String,
        mobileNo: String,
        emailId: String,
        password: String,
        callback: SignupContract.ResponseCallback
    ) {
        val url = "$BASE_URL$End_POINT_USER_REGISTER"
        val requestQueue = Volley.newRequestQueue(context)

        val userData = JSONObject().apply {
            put("full_name", fullName)
            put("mobile_no", mobileNo)
            put("email_id", emailId)
            put("password", password)
        }

        val jsonObjectRequest = object : JsonObjectRequest(
            Method.POST, url, userData,
            Response.Listener { response ->
                val status = response.getInt("status")
                val message = response.getString("message")
                callback.onResponse(status, message)
            },
            Response.ErrorListener { error ->
                val errorMessage = error.message ?: "An error occurred"
                callback.onError(errorMessage)
            }) {

            override fun getHeaders(): MutableMap<String, String> {
                return HEADER
            }
        }
        requestQueue.add(jsonObjectRequest)
    }


    fun userLogin(
        context: Context,
        emailId: String,
        password: String,
        callback: LoginContract.ResponseCallback
    ) {
        val url = "$BASE_URL$End_POINT_USER_LOGIN"
        val requestQueue = Volley.newRequestQueue(context)

        val userData = JSONObject().apply {
            put("email_id", emailId)
            put("password", password)
        }

        val jsonObjectRequest = object : JsonObjectRequest(
            Method.POST, url, userData,
            Response.Listener { response ->
                val status = response.getInt("status")
                val message = response.getString("message")
                if (response.has("user")) {
                    val userObject = response.getJSONObject("user")
                    val userId = userObject.getString("user_id")
                    val fullName = userObject.getString("full_name")
                    val mobileNo = userObject.getString("mobile_no")
                    val userEmail = userObject.getString("email_id")
                    val user = User(userId, fullName, mobileNo, userEmail)

                    callback.onResponse(status, message, user)
                } else {
                    callback.onResponse(status, message, null)
                }
            },
            Response.ErrorListener { error ->
                val errorMessage = error.message ?: "An error occurred"
                callback.onError(errorMessage)
            }) {

            override fun getHeaders(): MutableMap<String, String> {
                return HEADER
            }
        }
        requestQueue.add(jsonObjectRequest)
    }










}