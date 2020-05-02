package com.example.cs4153pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_select_sides.*
import kotlin.math.roundToInt


class SelectSides : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_sides)

        cartText.text = "Subtotal: $" + Order.subtotal.toString()

        //Entrees and Sides
        btnAddSalad.setOnClickListener {
            Order.addSide("Salad")
            textSaladCount.text = "  " + Order.salads.toString() + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnRemoveSalad.setOnClickListener {
            Order.removeSide("Salad")
            textSaladCount.text = "  " + Order.salads.toString() + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnAddSticks.setOnClickListener {
            Order.addSide("Breadsticks")
            textStickCount.text = "  " + Order.breadsticks.toString() + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnRemoveSticks.setOnClickListener {
            Order.removeSide("Breadsticks")
            textStickCount.text = "  " + Order.breadsticks.toString() + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnDrinkSelect.setOnClickListener {
            val intent = Intent(this, SelectDrink::class.java)
            startActivity(intent)
        }
    }
}
