package com.example.cs4153pizzaapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pizza_customization.*
import kotlin.math.roundToInt


class PizzaCustomization : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_customization)

        cartText.text = "Subtotal: $" + Order.subtotal.toString()
        Log.d("cartText:", cartText.text.toString())

        for (meat in Order.meats) {
            if (meat=="Pepperoni") btnPepperoni.isChecked=true
            if (meat=="Sausage") btnSausage.isChecked=true
            if (meat=="Ham") btnHam.isChecked=true
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
            Log.d("cartText:", cartText.text.toString())
        }

        for (veggie in Order.veggies) {
            if (veggie=="Pepper") btnPeppers.isChecked=true
            if (veggie=="Onion") btnOnions.isChecked=true
            if (veggie=="Olive") btnOlives.isChecked=true
            if (veggie=="Pineapple") btnPineapples.isChecked=true
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
            Log.d("cartText:", cartText.text.toString())
        }

        // Respond to user input
        sauceSelector.setOnCheckedChangeListener() { _, sauceChoice : Int ->
            when(sauceChoice) {
                R.id.marinaraButton -> {
                    Order.sauce = "Marinara"
                }
                R.id.alfredoButton -> {
                    Order.sauce = "Alfredo"
                }
            }
        }

        btnPepperoni.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) {
                Order.addMeat("Pepperoni")
            }
            else {
                Order.removeMeat("Pepperoni")
            }
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
            Log.d("cartText:", cartText.text.toString())
        }

        btnSausage.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) {
                Order.addMeat("Sausage")
            }
            else {
                Order.removeMeat("Sausage")
            }
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
            Log.d("cartText:", cartText.text.toString())
        }

        btnHam.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) {
                Order.addMeat("Ham")
            }
            else {
                Order.removeMeat("Ham")
            }
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
            Log.d("cartText:", cartText.text.toString())
        }

        btnPeppers.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) {
                Order.addVeggie("Peppers")
            }
            else {
                Order.removeVeggie("Peppers")
            }
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
            Log.d("cartText:", cartText.text.toString())
        }

        btnOnions.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) {
                Order.addVeggie("Onions")
            }
            else {
                Order.removeVeggie("Onions")
            }
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
            Log.d("cartText:", cartText.text.toString())
        }

        btnOlives.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) {
                Order.addVeggie("Olives")
            }
            else {
                Order.removeVeggie("Olives")
            }
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
            Log.d("cartText:", cartText.text.toString())
        }

        btnPineapples.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) {
                Order.addVeggie("Pineapples")
            }
            else {
                Order.removeVeggie("Pineapples")
            }
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
            Log.d("cartText:", cartText.text.toString())
        }

        // Close activity
        btnConfirm.setOnClickListener {
            val intent = Intent(this, SelectSides::class.java)
            startActivity(intent)
        }
    }
}