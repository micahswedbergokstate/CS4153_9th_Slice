package com.example.cs4153pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_select_sides.*


class SelectSides : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_sides)

        cartText.text = "Cart: $" + Order.total.toString()

        //Entrees and Sides
        btnAddSalad.setOnClickListener {
            Order.addSide(Side("Salad"))
            textSaladCount.text = "  " + countItems("Salad") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnRemoveSalad.setOnClickListener {
            Order.removeSide("Salad")
            textSaladCount.text = "  " + countItems("Salad") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnAddSticks.setOnClickListener {
            Order.addSide(Side("Breadsticks"))
            textStickCount.text = "  " + countItems("Breadsticks") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnRemoveSticks.setOnClickListener {
            Order.removeSide("Breadsticks")
            textStickCount.text = "  " + countItems("Breadsticks") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnDrinkSelect.setOnClickListener {
            val intent = Intent(this, SelectDrink::class.java)
            startActivity(intent)
        }
    }

    fun countItems(item: String): Int {
        var count = 0
        for(i in Order.sides) {
            if (i.name==item) count++
        }
        return count
    }
}
