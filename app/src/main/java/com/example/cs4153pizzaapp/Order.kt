package com.example.cs4153pizzaapp

import android.util.Log

/*
Object to contain the pizza order.
 */

object Order {
    // Properties of an Order
    var pizza = ""
    var pizzaPrice = 0.00
    var sauce : String = ""
    var cheese : Boolean = true
    var veggies : MutableList<String> = ArrayList()
    var meats : MutableList<String> = ArrayList()

    var breadsticks : Int = 0
    var salads : Int = 0
    var drinks : MutableList<String> = ArrayList()
    var subtotal : Double = 0.0
    var tax : Double = 0.0
    var tip : Double = 0.0
    var total : Double = 0.00

    // Called each time an item is added or subtracted from the cart
    private fun calculateTotal() {
        this.subtotal = 0.0
        this.tax = 0.0
        this.total = 0.0

        this.subtotal += this.pizzaPrice
        Log.d("Pizza:", subtotal.toString())

        this.subtotal += (0.79 * this.veggies.size)
        Log.d("Veggies:", subtotal.toString())

        this.subtotal += (0.99 * this.meats.size)
        Log.d("Meats:", subtotal.toString())

        this.subtotal += (2.99 * breadsticks.toDouble())
        Log.d("Breadsticks:", subtotal.toString())

        this.subtotal += (1.99 * salads.toDouble())
        Log.d("Salads:", subtotal.toString())

        this.subtotal += (0.99 * this.drinks.size)
        Log.d("Drinks:", subtotal.toString())

        // Recalculate tax
        this.tax = this.subtotal * 0.07
        Log.d("Tax:", tax.toString())

        // Redo the total
        this.total = this.subtotal + this.tip + this.tax
        Log.d("Total:", subtotal.toString())
    }

    // Adding/removing pizzas
    fun addPizza(p : String) {
        this.pizza = p
        when(this.pizza) {
            "Cheese" -> {
                this.pizzaPrice = 7.99
                this.sauce = "Marinara"
            }
            "Pepperoni" -> {
                this.pizzaPrice = 8.99
                this.sauce = "Marinara"
                this.meats.add("Pepperoni")
            }
            "Sausage" -> {
                this.pizzaPrice = 8.99
                this.sauce = "Marinara"
                this.meats.add("Sausage")
            }
            "Hawaiian" -> {
                this.pizzaPrice = 9.99
                this.sauce = "Marinara"
                this.veggies.add("Pineapple")
                this.meats.add("Canadian Bacon")
            }
            "Veggie" -> {
                this.pizzaPrice = 10.99
                this.sauce = "Marinara"
                this.veggies.add("Peppers")
                this.veggies.add("Onions")
                this.veggies.add("Olives")
            }
            "Supreme" -> {
                this.pizzaPrice = 11.99
                this.sauce = "Marinara"
                this.meats.add("Sausage")
                this.meats.add("Pepperoni")
                this.veggies.add("Peppers")
                this.veggies.add("Onions")
                this.veggies.add("Olives")
            }
            "BBQ" -> {
                this.pizzaPrice = 10.99
                this.sauce = "BBQ"
                this.meats.add("Chicken")
            }
            "Alfredo" -> {
                this.pizzaPrice = 7.99
                this.sauce = "Alfredo"
            }
            "MYO" -> {
                this.pizzaPrice = 6.99
            }
        }
        this.calculateTotal()
    }

    // ---------------------------------------------------------------------------------------------
    // Add/remove veggies
    fun addVeggie(v : String) {
        this.veggies.add(v)
        this.calculateTotal()
    }
    fun removeVeggie(v : String) {
        try {
            var veggieIndex : Int = 100 // Arbitrary index that is unlikely
            for (i : Int in 0 until this.veggies.size) {
                if (this.veggies[i] == v) {
                    veggieIndex = i
                }
                else { }
            }
            if (veggieIndex != 100) {
                this.veggies.removeAt(veggieIndex)
            }
        } catch (e : Exception) { }
        this.calculateTotal()
    }

    // ---------------------------------------------------------------------------------------------
    // Add/remove meats
    fun addMeat(m : String) {
        this.meats.add(m)
        this.calculateTotal()
    }
    fun removeMeat(m : String) {
        try {
            var meatIndex : Int = 100 // Arbitrary index that is unlikely
            for (i : Int in 0 until this.meats.size) {
                if (this.meats[i] == m) {
                    meatIndex = i
                }
                else { }
            }
            if (meatIndex != 100) {
                this.meats.removeAt(meatIndex)
            }
        } catch (e : Exception) { }
        this.calculateTotal()
    }

    // ---------------------------------------------------------------------------------------------
    // Adding/removing sides
    fun addSide(s : String) {
        when (s) {
            "Breadsticks" -> {
                breadsticks++
            }
            "Salad" -> {
                salads++
            }
        }
        this.calculateTotal()
    }
    fun removeSide(s : String) {
        when (s) {
            "Breadsticks" -> {
                if (breadsticks > 0) {
                    breadsticks--
                }
                else {}
            }
            "Salad" -> {
                if (salads > 0) {
                    salads--
                }
                else {}
            }
        }
        this.calculateTotal()
    }

    // ---------------------------------------------------------------------------------------------
    // Adding/removing drinks
    fun addDrink(d : String) {
        this.drinks.add(d)
        this.calculateTotal()
    }
    fun removeDrink(d : String) {
        try {
            var drinkIndex : Int = 100 // Arbitrary index that is unlikely
            for (i : Int in 0 until this.drinks.size) {
                if (this.drinks.get(i) == d) {
                    drinkIndex = i
                }
                else { }
            }
            if (drinkIndex != 100) {
                this.drinks.removeAt(drinkIndex)
            }
        } catch (e : Exception) { }
        this.calculateTotal()
    }

    // Clear the order, since there is only one working Order object
    fun clearOrder() {
        this.pizza = ""
        this.veggies.clear()
        this.breadsticks = 0
        this.salads = 0
        this.drinks.clear()
        this.subtotal = 0.0
        this.tax = 0.0
        this.tip = 0.0
        this.total = 0.0
    }

    // For displaying the total elsewhere
    override fun toString() : String {
        return this.total.toString()
    }
}