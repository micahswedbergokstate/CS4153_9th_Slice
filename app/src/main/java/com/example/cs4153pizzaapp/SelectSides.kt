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

        cartText.text = "Subtotal: $" + Order.subtotal_print

        //Entrees and Sides
        btnAddSalad.setOnClickListener {
            Order.addSide("Salad")
            textSaladCount.text = "  " + Order.salads.toString() + "  "
            cartText.text = "Subtotal: $" + Order.subtotal_print
        }

        btnRemoveSalad.setOnClickListener {
            Order.removeSide("Salad")
            textSaladCount.text = "  " + Order.salads.toString() + "  "
            cartText.text = "Subtotal: $" + Order.subtotal_print
        }

        btnAddSticks.setOnClickListener {
            Order.addSide("Breadsticks")
            textStickCount.text = "  " + Order.breadsticks.toString() + "  "
            cartText.text = "Subtotal: $" + Order.subtotal_print
        }

        btnRemoveSticks.setOnClickListener {
            Order.removeSide("Breadsticks")
            textStickCount.text = "  " + Order.breadsticks.toString() + "  "
            cartText.text = "Subtotal: $" + Order.subtotal_print
        }

        btnDrinkSelect.setOnClickListener {
            val intent = Intent(this, SelectDrink::class.java)
            startActivity(intent)
        }
    }
}
