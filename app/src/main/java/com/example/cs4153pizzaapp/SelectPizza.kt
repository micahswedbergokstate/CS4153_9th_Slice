package com.example.cs4153pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_select_pizza.*
import kotlin.math.roundToInt

class SelectPizza : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_pizza)

        cartText.text = "Subtotal: $" + Order.subtotal_print

        //Pizza
        btnCheese.setOnClickListener {
            Order.addPizza("Cheese")
            cartText.text = "Subtotal: $" + Order.subtotal_print
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnPepperoni.setOnClickListener {
            Order.addPizza("Pepperoni")
            cartText.text = "Subtotal: $" + Order.subtotal_print
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnSausage.setOnClickListener {
            Order.addPizza("Sausage")
            cartText.text = "Subtotal: $" + Order.subtotal_print
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnHawaiian.setOnClickListener {
            Order.addPizza("Sausage")
            cartText.text = "Subtotal: $" + Order.subtotal_print
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnVeggie.setOnClickListener {
            Order.addPizza("Veggie")
            cartText.text = "Subtotal: $" + Order.subtotal_print
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnSupreme.setOnClickListener {
            Order.addPizza("Supreme")
            cartText.text = "Subtotal: $" + Order.subtotal_print
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnBBQ.setOnClickListener {
            Order.addPizza("BBQ")
            cartText.text = "Subtotal: $" + Order.subtotal_print
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnAlfredo.setOnClickListener {
            Order.addPizza("Alfredo")
            cartText.text = "Subtotal: $" + Order.subtotal_print
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnMYO.setOnClickListener {
            Order.addPizza("MYO")
            cartText.text = "Subtotal: $" + Order.subtotal_print
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }
    }
}
