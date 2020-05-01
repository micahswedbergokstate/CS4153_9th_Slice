package com.example.cs4153pizzaapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseManager(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "CS4153_9th_Slice", null, 1) {
    companion object {
        const val TABLE_NAME = "all_users"
        const val COLUMN_ID = "user_id"
        const val COLUMN_EMAIL = "user_email"
        const val COLUMN_PASSWORD = "user_password"
        private var instance: DatabaseManager? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseManager {
            if (instance == null) {
                instance = DatabaseManager(context.applicationContext)
            }

            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Create database table
        db?.createTable(
            TABLE_NAME,
            true,
            COLUMN_ID to INTEGER + PRIMARY_KEY + UNIQUE,
            COLUMN_EMAIL to TEXT,
            COLUMN_PASSWORD to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        // Upgrade database table
        db?.dropTable(TABLE_NAME, true)
    }

}

// Access property for Context
val Context.database: DatabaseManager
    get() = DatabaseManager.getInstance(applicationContext)
