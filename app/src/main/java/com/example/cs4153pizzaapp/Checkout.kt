package com.example.cs4153pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_checkout.*

class Checkout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

            btnConfirmOrder.setOnClickListener {

                //Either checkout success, or checkout failure
                var success = true
                if(success) {
                    val intent = Intent(this, OrderConfirmation::class.java)
                    startActivity(intent)
                }
                else {
                    //Order Failure
                }

            }
    }
}
