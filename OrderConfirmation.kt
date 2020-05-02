package com.example.cs4153pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_order_confirmation.*

class OrderConfirmation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

            btnHome.setOnClickListener {
                val intent = Intent(this, Home::class.java)
                Order.clearOrder()
                startActivity(intent)
            }
    }
}
