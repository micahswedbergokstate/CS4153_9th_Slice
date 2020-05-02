package com.example.cs4153pizzaapp

/*
Object to contain the pizza order, as well as Pizza, Side, and Drink classes.
 */

object Order {
    // Properties of an Order
    var pizza : Pizza = Pizza("None")
    var veggies : MutableList<String> = ArrayList()
    var meats : MutableList<String> = ArrayList()

    var sides : MutableList<Side> = ArrayList()
    var drinks : MutableList<Drink> = ArrayList()
    private var subtotal : Double = 0.0
    private var tax : Double = 0.0
    var tip : Double = 0.0
    var total : Double = 0.00

    // Called each time an item is added or subtracted from the cart
    private fun calculateTotal() {
        this.subtotal = 0.0
        this.tax = 0.0
        this.total = 0.0

        subtotal += pizza.price

        subtotal += (0.79 * this.veggies.size)

        subtotal += (0.99 * this.meats.size)

        for (i : Int in 0 until this.sides.size) {
            this.subtotal += this.sides.get(i).price
        }

        for (i : Int in 0 until this.drinks.size) {
            this.subtotal += this.drinks.get(i).price
        }

        // Recalculate tax
        this.tax = this.subtotal * 0.07

        // Redo the total
        this.total = this.subtotal + this.tip + this.tax
    }

    // Adding/removing pizzas
    fun addPizza(p : Pizza) {
        this.pizza = p
        when(this.pizza.pizzaType) {
            "Pepperoni" -> {
                this.meats.add("Pepperoni")
            }
            "Sausage" -> {
                this.meats.add("Sausage")
            }
            "Hawaiian" -> {
                this.veggies.add("Pineapple")
                this.meats.add("Canadian Bacon")
            }
            "Veggie" -> {
                this.veggies.add("Peppers")
                this.veggies.add("Onions")
                this.veggies.add("Olives")
            }
            "Supreme" -> {
                this.meats.add("Sausage")
                this.meats.add("Pepperoni")
                this.veggies.add("Peppers")
                this.veggies.add("Onions")
                this.veggies.add("Olives")
            }
            "BBQ" -> {
                this.meats.add("Chicken")
            }
        }
        this.calculateTotal()
    }

    // ---------------------------------------------------------------------------------------------
    // Add/remove veggies
    fun addVeggie(v : String) {
        this.veggies.add(v)
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
        calculateTotal()
    }

    // ---------------------------------------------------------------------------------------------
    // Add/remove meats
    fun addMeat(m : String) {
        this.meats.add(m)
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
        calculateTotal()
    }

    // ---------------------------------------------------------------------------------------------
    // Adding/removing sides
    fun addSide(s : Side) {
        this.sides.add(s)
        this.calculateTotal()
    }
    fun removeSide(s : String) {
        try {
            var sideIndex : Int = 100 // Arbitrary index that is unlikely
            for (i : Int in 0 until this.sides.size) {
                if (this.sides[i].name == s) {
                    sideIndex = i
                }
                else { }
            }
            if (sideIndex != 100) {
                this.sides.removeAt(sideIndex)
            }
        } catch (e : Exception) { }
        this.calculateTotal()
    }

    // ---------------------------------------------------------------------------------------------
    // Adding/removing drinks
    fun addDrink(d : String) {
        var drink = Drink(d)
        this.drinks.add(drink)
        this.calculateTotal()
    }
    fun removeDrink(d : String) {
        try {
            var drinkIndex : Int = 100 // Arbitrary index that is unlikely
            for (i : Int in 0 until this.drinks.size) {
                if (this.drinks[i].name == d) {
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
        this.pizza = Pizza("None")
        this.sides.clear()
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

// =================================================================================================
// Pizza Object
data class Pizza(var pizzaType : String) {
    // Properties of a pizza
    var price : Double = 0.0
    var cheese : Boolean = true
    var sauce : String = ""

    constructor(pizzaType : String,
                cheese : Boolean,
                sauce : String,
                veggies : List<String>,
                meats : List<String>,
                price : Double): this(pizzaType) {
        // Defaults. All can be customized when ordering except price.
        when(pizzaType) {
            "Cheese" -> {
                this.price = 7.99
                this.sauce = "Marinara"
            }
            "Pepperoni" -> {
                this.price = 8.99
                this.sauce = "Marinara"
            }
            "Sausage" -> {
                this.price = 8.99
                this.sauce = "Marinara"
            }
            "Hawaiian" -> {
                this.price = 9.99
                this.sauce = "Marinara"
            }
            "Veggie" -> {
                this.price = 10.99
                this.sauce = "Marinara"
            }
            "Supreme" -> {
                this.price = 11.99
                this.sauce = "Marinara"
            }
            "BBQ" -> {
                this.price = 10.99
                this.sauce = "BBQ"
            }
            "Alfredo" -> {
                this.price = 7.99
                this.sauce = "Alfredo"
            }
            "MYO" -> {
                this.price = 6.99
                this.cheese = true
            }

            "None" -> { }
        }

    }

    override fun toString() : String {
        var printout = ""
        printout += this.pizzaType
        printout += " pizza with "
        // Add cheese if applicable
        when(this.cheese) {
            true -> {
                printout += "cheese, "
            }
            false -> {}
        }
        // Add sauce
        printout += this.sauce

        return printout
    }
}

// =================================================================================================
// Side object

data class Side(var name : String) {
    var price : Double = 0.0
    constructor(name : String,
                price : Double) : this(name) {
        when(name) {
            "Breadsticks" -> this.price = 2.99
            "Salad" -> this.price = 1.99
        }
    }

    override fun toString(): String {
        return this.name
    }
}

// =================================================================================================
// Drink object

data class Drink(var name : String) {
    // All drinks are the same price, but this simplifies price calculation in an Order
    var price : Double = 0.99

    override fun toString(): String {
        return this.name
    }
}