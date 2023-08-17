package com.example.emarket.model.local.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.emarket.model.local.DatabaseConstants.COLUMN_DESCRIPTION
import com.example.emarket.model.local.DatabaseConstants.COLUMN_ID
import com.example.emarket.model.local.DatabaseConstants.COLUMN_NAME
import com.example.emarket.model.local.DatabaseConstants.COLUMN_PRICE
import com.example.emarket.model.local.DatabaseConstants.COLUMN_QUANTITY
import com.example.emarket.model.local.DatabaseConstants.COLUMN_URL
import com.example.emarket.model.local.DatabaseConstants.TABLE_CART
import com.example.emarket.model.local.DatabaseHelper
import com.example.emarket.model.local.entity.Item
import com.example.emarket.model.local.entity.Product

class CartDao(context: Context) {
    private val dbHelper: DatabaseHelper = DatabaseHelper(context)

    fun insertOrUpdateCartItem(product: Product, changeQuantityBy: Int) {
        val db = dbHelper.writableDatabase
        val existingItem = getItemById(product.product_id)
        if (existingItem == null) {
            val values = ContentValues().apply {
                put(COLUMN_ID, product.product_id)
                put(COLUMN_NAME, product.product_name)
                put(COLUMN_DESCRIPTION, product.description)
                put(COLUMN_URL, product.product_image_url)
                put(COLUMN_PRICE, product.price)
                put(COLUMN_QUANTITY, changeQuantityBy)
            }
            db.insert(TABLE_CART, null, values)
        } else {
            val newQuantity = getQuantityForProduct(product.product_id) + changeQuantityBy
            if (newQuantity > -1) {
                val values = ContentValues().apply {
                    put(COLUMN_QUANTITY, newQuantity)
                }
                db.update(TABLE_CART, values, "$COLUMN_ID = ?", arrayOf(product.product_id))
            } else {
                db.delete(TABLE_CART, "$COLUMN_ID = ?", arrayOf(product.product_id))
            }
        }
    }

    fun getItemById(productId: String): Item? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            TABLE_CART,
            null,
            "$COLUMN_ID = ?",
            arrayOf(productId),
            null, null, null
        )
        val item: Item? = if (cursor != null && cursor.moveToFirst()) {
            val id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
            val url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL))
            val price = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRICE))
            val quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY))
            Item(amount = "${quantity*price}", quantity = quantity.toString(), description = description, product_id = id, product_image_url = url, product_name = name, unit_price = price.toString())
        } else {
            null
        }
        cursor?.close()
        return item
    }

    fun deleteProduct(productId: String) {
        val db = dbHelper.writableDatabase
        db.delete(TABLE_CART, "$COLUMN_ID = ?", arrayOf(productId))
    }

    fun getAllItems(): MutableList<Item> {
        val db = dbHelper.readableDatabase
        val items = mutableListOf<Item>()
        val cursor: Cursor? = db.query(TABLE_CART, null, null, null, null, null, null)
        cursor?.use {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                val url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL))
                val price = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRICE))
                val quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY))
                items.add(Item(amount = "${quantity*price}", quantity = quantity.toString(), description = description, product_id = id, product_image_url = url, product_name = name, unit_price = price.toString()))
            }
        }
        cursor?.close()
        return items
    }

    fun getQuantityForProduct(productId: String): Int {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            TABLE_CART,
            arrayOf(COLUMN_QUANTITY),
            "$COLUMN_ID = ?",
            arrayOf(productId),
            null, null, null
        )
        var quantity = 0
        cursor?.use {
            if (cursor.moveToFirst()) {
                quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY))
            }
        }
        cursor?.close()
        return quantity
    }

    fun updateQuantityForProduct(productId: String, incrementBy: Int) {
        val existingItem = getItemById(productId)
        if (existingItem != null) {
            val newQuantity = getQuantityForProduct(productId) + incrementBy
            if (newQuantity > -1) {
                val db = dbHelper.writableDatabase
                val values = ContentValues().apply {
                    put(COLUMN_QUANTITY, newQuantity)
                }
                db.update(TABLE_CART, values, "$COLUMN_ID = ?", arrayOf(productId))
                db.close()
            }
        }
    }

    fun calculateTotalPriceForAllProducts(): Int {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT SUM($COLUMN_PRICE * $COLUMN_QUANTITY) FROM $TABLE_CART", null)
        var totalPrice = 0
        cursor?.use {
            if (cursor.moveToFirst()) {
                totalPrice = cursor.getInt(0)
            }
        }
        cursor?.close()
        return totalPrice
    }

    fun calculateTotalPriceForProduct(productId: String): Int {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            TABLE_CART,
            arrayOf(COLUMN_PRICE, COLUMN_QUANTITY),
            "$COLUMN_ID = ?",
            arrayOf(productId),
            null, null, null
        )
        var totalPrice = 0
        cursor?.use {
            if (cursor.moveToFirst()) {
                val price = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRICE))
                val quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY))
                totalPrice = price * quantity
            }
        }
        cursor?.close()
        return totalPrice
    }

    fun clearAllEntries() {
        val db = dbHelper.writableDatabase
        db.delete(TABLE_CART, null, null)
    }
}
