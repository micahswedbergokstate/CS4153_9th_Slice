package com.example.cs4153pizzaapp

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pizza_customization.*


class PizzaCustomization : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_customization)

        cartText.text = "Cart: $" + Order.total.toString()

        //Access current working pizza
        //Order.pizzas.last()

        //populate check boxes
        if (Order.pizzas.last().sauce=="Marinara") btnMarinara.isChecked=true
        else if (Order.pizzas.last().sauce=="Alfredo") btnAlfredo.isChecked=true

        for (meat in Order.pizzas.last().meats) {
            if (meat=="Pepperoni") btnPepperoni.isChecked=true
            if (meat=="Sausage") btnSausage.isChecked=true
            if (meat=="Ham") btnHam.isChecked=true
        }

        for (veggie in Order.pizzas.last().veggies) {
            if (veggie=="Pepper") btnPeppers.isChecked=true
            if (veggie=="Onion") btnOnions.isChecked=true
            if (veggie=="Olive") btnOlives.isChecked=true
            if (veggie=="Pineapple") btnPineapples.isChecked=true
        }

        //respond to user input
        //I don't really know how this works
        btnMarinara.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.pizzas.last().sauce="Marinara"
        }

        btnAlfredo.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.pizzas.last().sauce = "Alfredo"
        }

        btnPepperoni.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.pizzas.last().addMeat("Pepperoni")
            else Order.pizzas.last().removeMeat("Pepperoni")
        }

        btnSausage.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.pizzas.last().addMeat("Sausage")
            else Order.pizzas.last().removeMeat("Sausage")
        }

        btnHam.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.pizzas.last().addMeat("Ham")
            else Order.pizzas.last().removeMeat("Ham")
        }

        btnPeppers.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.pizzas.last().addVeggies("Peppers")
            else Order.pizzas.last().removeVeggies("Peppers")
        }

        btnOnions.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.pizzas.last().addVeggies("Onions")
            else Order.pizzas.last().removeVeggies("Onions")
        }

        btnOlives.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.pizzas.last().addVeggies("Olives")
            else Order.pizzas.last().removeVeggies("Olives")
        }

        btnPineapples.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) Order.pizzas.last().addVeggies("Pineapples")
            else Order.pizzas.last().removeVeggies("Pineapples")
        }

        //close activity
        btnConfirm.setOnClickListener {finish()}
    }
}

