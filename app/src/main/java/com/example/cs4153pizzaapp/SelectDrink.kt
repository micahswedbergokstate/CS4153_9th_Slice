package com.example.cs4153pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_select_drink.*

class SelectDrink : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_drink)

        cartText.text = "Cart: $" + Order.total.toString()

        btnReviewOrder.setOnClickListener {
            val intent = Intent(this, ReviewOrder::class.java)
            startActivity(intent)
        }

    }
}
