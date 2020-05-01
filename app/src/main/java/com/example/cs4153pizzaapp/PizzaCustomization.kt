package com.example.cs4153pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pizza_customization.*

class PizzaCustomization : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_customization)

        cartText.text = "Cart: $" + Order.total.toString()

        //Access current working pizza
        //Order.pizzas.last()

        btnConfirm.setOnClickListener {finish()}
    }
}

