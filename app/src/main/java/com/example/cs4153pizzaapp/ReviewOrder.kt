package com.example.cs4153pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_review_order.*

class ReviewOrder : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_order)

        btnHome.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        btnCheckout.setOnClickListener {
            val intent = Intent(this, Checkout::class.java)
            startActivity(intent)
        }
    }
}
