package com.example.cs4153pizzaapp

/*
Object to contain the pizza order, as well as Pizza, Side, and Drink classes.
 */

object Order {
    // Properties of an Order
    var pizzas : MutableList<Pizza> = ArrayList()
    var sides : MutableList<Side> = ArrayList()
    var drinks : MutableList<Drink> = ArrayList()
    private var subtotal : Double = 0.0
    private var tax : Double = 0.0
    var tip : Double = 0.0
    var total : Double = 0.0

    // Called each time an item is added or subtracted from the cart
    private fun calculateTotal() {
        this.subtotal = 0.0
        this.tax = 0.0
        this.total = 0.0
        for (i : Int in 0 until this.pizzas.size) {
            this.subtotal += this.pizzas.get(i).price
        }
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
        this.pizzas.add(p)
        this.calculateTotal()
    }
    fun removePizza(p : Pizza) {
        try {
            this.pizzas.remove(p)
        } catch (e : Exception) { } // In case the pizza doesn't exist
        this.calculateTotal()
    }

    // Adding/removing sides
    fun addSide(s : Side) {
        this.sides.add(s)
        this.calculateTotal()
    }
    fun removeSide(s : Side) {
        try {
            this.sides.remove(s)
        } catch (e : Exception) { }
        this.calculateTotal()
    }

    // Adding/removing drinks
    fun addDrink(d : Drink) {
        this.drinks.add(d)
        this.calculateTotal()
    }
    fun removeDrink(d : Drink) {
        try {
            this.drinks.remove(d)
        } catch (e : Exception) { }
        this.calculateTotal()
    }

    // Clear the order, since there is only one working Order object
    fun clearOrder() {
        this.pizzas.clear()
        this.sides.clear()
        this.drinks.clear()
        this.subtotal = 0.0
        this.tax = 0.0
        this.tip = 0.0
        this.total = 0.0
    }
}

// =================================================================================================
// Pizza Object
data class Pizza(var pizzaType : String) {
    // Properties of a pizza
    var price : Double = 0.0
    var cheese : Boolean = true
    var sauce : String = ""
    var veggies : MutableList<String> = ArrayList()
    var meats : MutableList<String> = ArrayList()

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
                this.meats.add("Pepperoni")
            }
            "Sausage" -> {
                this.price = 8.99
                this.sauce = "Marinara"
                this.meats.add("Sausage")
            }
            "Hawaiian" -> {
                this.price = 9.99
                this.sauce = "Marinara"
                this.veggies.add("Pineapple")
                this.meats.add("Canadian Bacon")
            }
            "Veggie" -> {
                this.price = 10.99
                this.sauce = "Marinara"
                this.veggies.add("Peppers")
                this.veggies.add("Onions")
                this.veggies.add("Olives")
            }
            "Supreme" -> {
                this.price = 11.99
                this.sauce = "Marinara"
                this.meats.add("Sausage")
                this.meats.add("Pepperoni")
                this.veggies.add("Peppers")
                this.veggies.add("Onions")
                this.veggies.add("Olives")
            }
            "BBQ" -> {
                this.price = 10.99
                this.sauce = "BBQ"
                this.meats.add("Chicken")
            }
            "Alfredo" -> {
                this.price = 7.99
                this.sauce = "Alfredo"
            }
        }

        // -----------------------------------------------------------------------------------------
        // Functions for adding/subtracting elements which will also modify price

        // Cheese
        fun addCheese() {
            if (!this.cheese) {
                this.cheese = true
                this.price += 0.49
            }
            else { }
        }
        fun omitCheese() {
            if (this.cheese) {
                this.cheese = false
                this.price -= 0.49
            }
            else { }
        }

        // Sauce variable can simply be modified; no price difference

        // Veggies
        fun addVeggie(v : String) {
            this.price += 0.79
            this.veggies.add(v)
        }
        fun removeVeggie(v : String) {
            try {
                this.veggies.remove(v)
                this.price -= 0.79
            } catch (e : Exception) { } // In case the specified veggie doesn't exist
        }

        // Meat
        fun addMeat(m : String) {
            this.price += 0.99
            this.meats.add(m)
        }
        fun removeMeat(m : String) {
            try {
                this.meats.remove(m)
                this.price -= 0.99
            } catch (e : Exception) { } // In case the specified meat doesn't exist
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
        printout += ", "
        // Add veggie toppings
        for(element in this.veggies) {
            var veggie : String = element
            veggie += ", "
            printout += veggie
        }
        // Add meat toppings
        for(element in this.meats) {
            var meat : String = element
            meat += ", "
            printout += meat
        }

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