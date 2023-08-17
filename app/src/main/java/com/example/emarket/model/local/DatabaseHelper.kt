package com.example.emarket.model.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.emarket.model.local.DatabaseConstants.CREATE_CART_TABLE
import com.example.emarket.model.local.DatabaseConstants.DATABASE_NAME
import com.example.emarket.model.local.DatabaseConstants.DATABASE_VERSION
import com.example.emarket.model.local.DatabaseConstants.DROP_CART_TABLE

class DatabaseHelper(private val context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_CART_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 3 && newVersion >=3) {
            db?.execSQL(DROP_CART_TABLE)
            db?.execSQL(CREATE_CART_TABLE)
        }
    }
}