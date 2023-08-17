package com.example.emarket.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.emarket.model.local.entity.Product
import com.example.emarket.view.ViewConstants

object AppUtils {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun getSharedPrefsBoolean(context: Context, fileName: String, key: String, defVal: Boolean = false) : Boolean {
        val sharedPreferences = context.getSharedPreferences(fileName, AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, defVal)
    }

    fun setSharedPrefsBoolean(context: Context, fileName: String, key: String, value: Boolean = false) {
        val sharedPreferences = context.getSharedPreferences(fileName, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getSharedPrefsInt(context: Context, fileName: String, key: String, defVal: Int = 0) : Int {
        val sharedPreferences = context.getSharedPreferences(fileName, AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getInt(key, defVal)
    }

    fun setSharedPrefsInt(context: Context, fileName: String, key: String, value: Int = 1) {
        val sharedPreferences = context.getSharedPreferences(fileName, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getSharedPrefsString(context: Context, fileName: String, key: String, defVal: String = "") : String {
        val sharedPreferences = context.getSharedPreferences(fileName, AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getString(key, defVal) ?: defVal
    }

    fun setSharedPrefsString(context: Context, fileName: String, key: String, value: String = "") {
        val sharedPreferences = context.getSharedPreferences(fileName, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun deleteSharedPrefs(context: Context, fileName: String) {
        val sharedPreferences = context.getSharedPreferences(fileName, AppCompatActivity.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }

    fun navigateToActivity(context: Context, activity: Class<out Activity>) {
        val intent = Intent(context, activity)
        context.startActivity(intent)
        (context as Activity).finish()
    }

    fun navigateToFragment(activity: AppCompatActivity, container: Int, fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .addToBackStack(null)
            .commit()
    }
}