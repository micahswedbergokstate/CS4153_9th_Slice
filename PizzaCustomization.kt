package com.example.cs4153pizzaapp

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pizza_customization.*
import kotlin.math.roundToInt


class PizzaCustomization : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_customization)

        cartText.text = "Cart: $" + Order.total.roundToInt().toString()

        //populate check boxes
        if (Order.pizza.sauce=="Marinara") btnMarinara.isChecked=true
        else if (Order.pizza.sauce=="Alfredo") btnAlfredo.isChecked=true

        for (meat in Order.meats) {
            if (meat=="Pepperoni") btnPepperoni.isChecked=true
            if (meat=="Sausage") btnSausage.isChecked=true
            if (meat=="Ham") btnHam.isChecked=true
        }

        for (veggie in Order.veggies) {
            if (veggie=="Pepper") btnPeppers.isChecked=true
            if (veggie=="Onion") btnOnions.isChecked=true
            if (veggie=="Olive") btnOlives.isChecked=true
            if (veggie=="Pineapple") btnPineapples.isChecked=true
        }

        //respond to user input
        //I don't really know how this works
        btnMarinara.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.pizza.sauce="Marinara"
        }

        btnAlfredo.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.pizza.sauce = "Alfredo"
        }

        btnPepperoni.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.addMeat("Pepperoni")
            else Order.removeMeat("Pepperoni")
        }

        btnSausage.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.addMeat("Sausage")
            else Order.removeMeat("Sausage")
        }

        btnHam.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.addMeat("Ham")
            else Order.removeMeat("Ham")
        }

        btnPeppers.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.addVeggie("Peppers")
            else Order.removeVeggie("Peppers")
        }

        btnOnions.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.addVeggie("Onions")
            else Order.removeVeggie("Onions")
        }

        btnOlives.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.addVeggie("Olives")
            else Order.removeVeggie("Olives")
        }

        btnPineapples.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.addVeggie("Pineapples")
            else Order.removeVeggie("Pineapples")
        }

        //close activity
        btnConfirm.setOnClickListener {finish()}
    }
}

