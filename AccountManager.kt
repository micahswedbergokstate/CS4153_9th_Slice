package com.example.cs4153pizzaapp

import android.util.Patterns
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import java.math.BigInteger
import java.security.MessageDigest

// USAGE:
// 
// If you want to determine if the current user is a logged in user:
//      if (AccountManager.getCurrentUserType() == AccountManager.AccountType.USER) {
//          // do stuff if they are a logged in user
//      }
//
// And for detecting if they are a guest:
//      if (AccountManager.getCurrentUserType() == AccountManager.AccountType.GUEST) {
//          // do stuff if they are a guest
//      }
//
// If you want to write a simple message, like "Thank you, <user/guest>", then just use
//      var user = AccountManager.getUser()
// This will return a string with either an email address, or the word "Guest", depending on current type.

object AccountManager {

    enum class UserType {
        USER, GUEST
    }

    const val SENDER_EMAIL = "ninthslice@gmail.com"
    const val SENDER_PASSWORD = "password123*"
    const val SEND_MAIL_SUBJECT = "Your Order is being crafted!"
    const val SEND_MAIL_BODY = "Thank you for ordering at Ninth Slice! "

    private val GUEST_NAME = "Guest"
    private var currentUserType = UserType.GUEST
    private var currentUser = GUEST_NAME

    // Get the current user type
    fun getCurrentUserType(): UserType {
        return currentUserType
    }

    // Get the current user email or "Guest"
    fun getUser(): String {
        return currentUser
    }

    // Set the user, null value logs the user out.
    fun setUser(email: String?) {
        if (email != null) {
            currentUser = email
            currentUserType = UserType.USER
        } else {
            currentUser = GUEST_NAME
            currentUserType = UserType.GUEST
        }
    }

    // Check a string to see if it represents a valid email address.
    // Actual existence of the email is not verified.
    fun isValidEmail(userInput: String): Boolean {
        return if (userInput.isBlank()) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(userInput).matches()
        }
    }

    // Check a string to see if it meets our requirements for a password:
    // 1. 8-32 characters in length
    // 2. Can contain numbers
    // 3. Can contain uppercase letters
    // 4. Can contain lowercase letters
    fun isValidPassword(userInput: String): Boolean {
        // Simple regex, modify password requirements here.
        val regex = Regex("^[a-zA-Z0-9]*$")
        return if (userInput.isBlank() || userInput.length < 8 || userInput.length > 32) {
            false
        } else {
            userInput.matches(regex)
        }
    }

    // Attempt to get a user's stored hash
    fun getStoredHash(db: DatabaseManager, email: String): String? {

        val data = getUserByEmail(db, email)    // The user's email, used to lookup the record
        var result: String? = null              // The password hash, null if email does not exist

        // No data was returned from the record lookup, return null
        if (data == null || data.isEmpty()) {
            return null
        }

        var numFound = 0                        // Should never be duplicates, but check anyway

        for (element in data) {                 // Check all the records returned
            if (element.first == email) {       // Check against our email parameter
                numFound += 1                   // Count it
                result = element.second         // Result is the last matching record's hash
            }
        }

        return if (numFound == 1) {
            // If we found only one record, return that record's hash
            result
        } else {
            // Otherwise, return null, can't return multiple hashes
            null
        }
    }

    // Check to see if a user exists
    fun userExists(db: DatabaseManager, searchEmail: String): Boolean {
        val data = getUserByEmail(db, searchEmail)  // Look up the record using the email field
        return !(data == null || data.isEmpty())    // No data means the record does not exist
    }

    // Attempt to get a record by looking up the email
    fun getUserByEmail(db: DatabaseManager, searchEmail: String): List<Pair<String, String>>? {
        var results: List<Pair<String, String>>? = null // The result
        db.use {
            // Lookup the email
            results = select(DatabaseManager.TABLE_NAME)
                .whereArgs("${DatabaseManager.COLUMN_EMAIL} = {email}", "email" to searchEmail)
                .parseList(rowParser { id: Int, username: String, password: String ->
                    Pair(username, password)    // We're returning a list of pairs
                })
        }
        return results  // Return all the records, if any
    }

    // Create a user, assumes email and password have been validated
    fun createUser(db: DatabaseManager, email: String, pass: String) {
        db.use {
            insert(
                DatabaseManager.TABLE_NAME,
                DatabaseManager.COLUMN_EMAIL to email,
                DatabaseManager.COLUMN_PASSWORD to pass
            )
        }
    }

    // Get the md5 hash of a String
    fun stringToMD5(str: String): String {
        val algorithm = MessageDigest.getInstance("MD5")
        return BigInteger(1, algorithm.digest(str.toByteArray()))
            .toString(16)
            .padStart(32, '0')
    }
}

