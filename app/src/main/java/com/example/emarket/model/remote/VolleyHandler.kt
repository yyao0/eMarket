package com.example.currentnews.model.remote

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.currentnews.model.entity.News
import com.example.currentnews.model.entity.NewsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object VolleyHandler {

    const val BASE_URL_LATEST_NEWS = "https://api.currentsapi.services/v1/latest-news"
    const val BASE_URL_SEARCH_NEWS = "https://api.currentsapi.services/v1/search"

    fun getLatestNews(context: Context, callback: (List<News>?, String?) -> Unit) {
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object: StringRequest(
            Request.Method.GET,
            BASE_URL_LATEST_NEWS,
            {
                val typeToken = object : TypeToken<NewsResponse>() {}
                val response = Gson().fromJson(it, typeToken)

                if (response.status == "ok"){
                    callback(response.news, null)
                }
            }, {
                callback(null, it.toString())
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val header = HashMap<String, String>()
                header["Authorization"] = "YP1NH-qY37RyEt7qSI5TDbUBijpGtMfV_186Sn_1p_0Nh_bV"
                return header
            }
        }
        requestQueue.add(stringRequest)
    }

    fun searchNews(context: Context, keyword: String, callback: (List<News>?, String?) -> Unit) {
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = object: StringRequest(
            Request.Method.GET,
            //BASE_URL_SEARCH_NEWS,
            "$BASE_URL_SEARCH_NEWS?keywords=$keyword",
            {
                val typeToken = object : TypeToken<NewsResponse>() {}
                val response = Gson().fromJson(it, typeToken)
                if (response.status == "ok"){
                    callback(response.news, null)
                }
            }, {
                callback(null, it.toString())
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val header = HashMap<String, String>()
                header["Authorization"] = "YP1NH-qY37RyEt7qSI5TDbUBijpGtMfV_186Sn_1p_0Nh_bV"
                return header
            }

            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["keywords"] = keyword
                return params
            }
        }
        requestQueue.add(stringRequest)
    }





}