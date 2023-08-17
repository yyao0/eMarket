package com.example.emarket.model.local

object DatabaseConstants {
    const val DATABASE_NAME = "eMarket"
    const val DATABASE_VERSION = 1
    const val TABLE_CART = "cart"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_DESCRIPTION = "description"
    const val COLUMN_URL = "url"
    const val COLUMN_PRICE = "price"
    const val COLUMN_QUANTITY = "quantity"

    val CREATE_CART_TABLE = """
        CREATE TABLE $TABLE_CART (
        $COLUMN_ID TEXT PRIMARY KEY,
        $COLUMN_NAME TEXT,
        $COLUMN_DESCRIPTION TEXT,
        $COLUMN_URL TEXT,
        $COLUMN_PRICE INTEGER,
        $COLUMN_QUANTITY INTEGER)
        """.trimIndent()

    val DROP_CART_TABLE = """
        DROP TABLE IF EXISTS $TABLE_CART
        """.trimIndent()
}