package com.example.cs4153pizzaapp

import android.R.interpolator.linear
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_review_order.*


class ReviewOrder : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_order)


        //hopefully an example of how to generate the order view. Need to be able to run the app before this can be tested though
        /*for (i in 1..20) {
            val btn = Button(this)
            btn.id = i
            val id: Int = btn.id
            btn.text="button $id"
            btn.layoutParams= LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

            var btn1 = findViewById<View>(id) as Button
            btn1.setOnClickListener { view ->
                Toast.makeText(
                    view.context,
                    "Button clicked index = $id", Toast.LENGTH_SHORT
                ).show()
            }
        }*/


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