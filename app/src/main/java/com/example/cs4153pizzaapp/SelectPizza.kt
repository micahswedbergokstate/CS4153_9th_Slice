package com.example.cs4153pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_select_pizza.*

class SelectPizza : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_pizza)

        cartText.text = "Cart: $" + Order.total.toString()

        //Pizza
        btnCheese.setOnClickListener {
            Order.addPizza(Pizza("Cheese"))
            cartText.text = "Cart: $" + Order.total.toString()
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnPepperoni.setOnClickListener {
            Order.addPizza(Pizza("Pepperoni"))
            cartText.text = "Cart: $" + Order.total.toString()
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnSausage.setOnClickListener {
            Order.addPizza(Pizza("Sausage"))
            cartText.text = "Cart: $" + Order.total.toString()
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnHawaiian.setOnClickListener {
            Order.addPizza(Pizza("Hawaiian"))
            cartText.text = "Cart: $" + Order.total.toString()
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnVeggie.setOnClickListener {
            Order.addPizza(Pizza("Veggie"))
            cartText.text = "Cart: $" + Order.total.toString()
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnSupreme.setOnClickListener {
            Order.addPizza(Pizza("Supreme"))
            cartText.text = "Cart: $" + Order.total.toString()
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnBBQ.setOnClickListener {
            Order.addPizza(Pizza("BBQ"))
            cartText.text = "Cart: $" + Order.total.toString()
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnAlfredo.setOnClickListener {
            Order.addPizza(Pizza("Alfredo"))
            cartText.text = "Cart: $" + Order.total.toString()
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnMYO.setOnClickListener {
            Order.addPizza(Pizza("MYO"))
            cartText.text = "Cart: $" + Order.total.toString()
            val intent = Intent(this, PizzaCustomization::class.java)
            startActivity(intent)
        }

        btnSideSelect.setOnClickListener {
            val intent = Intent(this, SelectSides::class.java)
            startActivity(intent)
        }
    }
}
