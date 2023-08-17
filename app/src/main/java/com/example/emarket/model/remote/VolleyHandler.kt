package com.example.emarket.model.remote

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.emarket.model.local.entity.Addresse
import com.example.emarket.model.local.entity.Category
import com.example.emarket.model.local.entity.Product
import com.example.emarket.model.local.entity.Subcategory
import com.example.emarket.model.local.entity.User
import com.example.emarket.presenter.CategoryContract
import com.example.emarket.presenter.CheckoutDeliveryContract
import com.example.emarket.presenter.LoginContract
import com.example.emarket.presenter.MainContract
import com.example.emarket.presenter.ProductBySubcategoryContract
import com.example.emarket.presenter.ProductDetailsContract
import com.example.emarket.presenter.SignupContract
import com.example.emarket.presenter.SubcategoryContract
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

object VolleyHandler {

    const val BASE_URL = "http://192.168.0.17/myshop/index.php/"
    const val End_POINT_USER_REGISTER =  "User/register"
    const val End_POINT_USER_LOGIN =  "User/auth"
    const val End_POINT_USER_LOGOUT =  "User/logout"
    const val End_POINT_USER_ADDRESS =  "User/addresses/"
    const val End_POINT_ADD_ADDRESS =  "User/address"
    const val End_POINT_CATEGORY =  "Category"
    const val End_POINT_SUBCATEGORY =  "SubCategory?category_id="
    const val END_POINT_SUBCATEGORY_PRODUCTS = "SubCategory/products/"
    const val END_POINT_PRODUCT_DETAILS = "Product/details/"
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


    fun userLogout(
        context: Context,
        emailId: String,
        callback: MainContract.LogoutCallback
        ) {
            val url = "$BASE_URL$End_POINT_USER_LOGOUT"
            val requestQueue = Volley.newRequestQueue(context)

            val userData = JSONObject().apply {
                put("email_id", emailId)
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

    fun getCategory(context: Context, callback: CategoryContract.ReponseCallback) {
        val url = "$BASE_URL$End_POINT_CATEGORY"
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val status = response.getInt("status")
                val message = response.getString("message")

                if (status == 0) {
                    val categoriesArray = response.getJSONArray("categories")
                    val categoriesList = mutableListOf<Category>()
                    for (i in 0 until categoriesArray.length()) {
                        val categoryObject = categoriesArray.getJSONObject(i)
                        val categoryId = categoryObject.getString("category_id")
                        val categoryName = categoryObject.getString("category_name")
                        val categoryImageUrl = categoryObject.getString("category_image_url")
                        val isActive = categoryObject.getString("is_active")

                        val category =
                            Category(categoryId, categoryName, categoryImageUrl, isActive)
                        categoriesList.add(category)
                    }
                    callback.onResponse(status, message, categoriesList)
                } else {
                    callback.onResponse(status, message, null)
                }
            },
            { error ->
                val errorMessage = error.message ?: "An error occurred"
                callback.onError(errorMessage)
            })
        requestQueue.add(jsonObjectRequest)
    }


    fun getSubcategory(context: Context, categoryId: String, callback: SubcategoryContract.ReponseCallback) {
        val url = "$BASE_URL$End_POINT_SUBCATEGORY$categoryId"
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val status = response.getInt("status")
                val message = response.getString("message")

                if (status == 0) {
                    val subcategoriesArray = response.getJSONArray("subcategories")
                    val subcategoriesList = mutableListOf<Subcategory>()
                    for (i in 0 until subcategoriesArray.length()) {
                        val subcategoryObject = subcategoriesArray.getJSONObject(i)
                        val subcategoryId = subcategoryObject.getString("subcategory_id")
                        val subcategoryName = subcategoryObject.getString("subcategory_name")
                        val categoryId = subcategoryObject.getString("category_id")
                        val subcategoryImageUrl = subcategoryObject.getString("subcategory_image_url")
                        val isActive = subcategoryObject.getString("is_active")
                        val subcategory = Subcategory(subcategoryId, subcategoryName, categoryId, subcategoryImageUrl, isActive)
                        subcategoriesList.add(subcategory)
                    }
                    callback.onResponse(status, message, subcategoriesList)
                } else {
                    callback.onResponse(status, message, null)
                }
            },
            { error ->
                val errorMessage = error.message ?: "An error occurred"
                callback.onError(errorMessage)
            })
        requestQueue.add(jsonObjectRequest)
    }

    fun getSubcategoryProducts(context: Context, subcategoryId: String, callback: ProductBySubcategoryContract.ReponseCallback) {
        val url = "$BASE_URL$END_POINT_SUBCATEGORY_PRODUCTS$subcategoryId"
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val status = response.getInt("status")
                val message = response.getString("message")
                if (status == 0) {
                    val productsArray = response.getJSONArray("products")
                    val productsType = object : TypeToken<List<Product>>() {}.type
                    val products = Gson().fromJson<List<Product>>(productsArray.toString(), productsType)
                    callback.onResponse(status, message, products)
                } else {
                    callback.onResponse(status, message, null)
                }
            },
            { error ->
                val errorMessage = error.message ?: "An error occurred"
                callback.onError(errorMessage)
            })
        requestQueue.add(jsonObjectRequest)
    }

    fun getProductDetails(context: Context, productId: String, callback: ProductDetailsContract.ReponseCallback) {
        val url = "$BASE_URL$END_POINT_PRODUCT_DETAILS$productId"
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val status = response.getInt("status")
                val message = response.getString("message")
                if (status == 0) {
                    val productJSON = response.getJSONObject("product")
                    val productType = object : TypeToken<Product>() {}.type
                    val product = Gson().fromJson<Product>(productJSON.toString(), productType)
                    callback.onResponse(status, message, product)
                } else {
                    callback.onResponse(status, message, null)
                }
            },
            { error ->
                val errorMessage = error.message ?: "An error occurred"
                callback.onError(errorMessage)
            })
        requestQueue.add(jsonObjectRequest)
    }

    fun getAddressesByUser(context: Context, userId: String, callback: CheckoutDeliveryContract.ReponseCallback) {
        val url = "$BASE_URL$End_POINT_USER_ADDRESS$userId"
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val status = response.getInt("status")
                val message = response.getString("message")
                if (status == 0) {
                    val addressesArray = response.getJSONArray("addresses")
                    val addressesType = object : TypeToken<List<Addresse>>() {}.type
                    val addresses = Gson().fromJson<List<Addresse>>(addressesArray.toString(), addressesType)
                    callback.onResponse(status, message, addresses)
                } else {
                    callback.onResponse(status, message, null)
                }
            },
            { error ->
                val errorMessage = error.message ?: "An error occurred"
                callback.onError(errorMessage)
            })
        requestQueue.add(jsonObjectRequest)
    }

    fun addAddress(
        context: Context,
        userId: String,
        title: String,
        address: String,
        callback: CheckoutDeliveryContract.ReponseCallback
    ) {
        val url = "${VolleyHandler.BASE_URL}${VolleyHandler.End_POINT_ADD_ADDRESS}"
        val requestQueue = Volley.newRequestQueue(context)
        val userData = JSONObject().apply {
            put("user_id", userId)
            put("title", title)
            put("address", address)
        }
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.POST, url, userData,
            Response.Listener { response ->
                val status = response.getInt("status")
                val message = response.getString("message")
                callback.onResponse(status, message, address = null)
            },
            Response.ErrorListener { error ->
                val errorMessage = error.message ?: "An error occurred"
                callback.onError(errorMessage)
            }) {

            override fun getHeaders(): MutableMap<String, String> {
                return VolleyHandler.HEADER
            }
        }
        requestQueue.add(jsonObjectRequest)
    }
}










