package com.example.cs4153pizzaapp

import android.accounts.Account
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_checkout.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class Checkout : AppCompatActivity() {

    lateinit var appExecutors: AppExecutors

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)


        btnConfirmOrder.setOnClickListener {

            //Either checkout success, or checkout failure
            var success = true
            if (success) {
                if (AccountManager.getCurrentUserType() == AccountManager.UserType.USER)
                sendEmail(AccountManager.getUser())
                val intent = Intent(this, OrderConfirmation::class.java)
                startActivity(intent)
            } else {
                //Order Failure
            }

        }
    }

    private fun sendEmail(toEmail: String) {
        appExecutors.diskIO().execute {
            val props = System.getProperties()
            props.put("mail.smtp.host", "smtp.gmail.com")
            props.put("mail.smtp.socketFactory.port", "465")
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            props.put("mail.smtp.auth", "true")
            props.put("mail.smtp.port", "465")

            val session = Session.getInstance(props)

            try {
                //Creating MimeMessage object
                val mm = MimeMessage(session)
                //Setting sender address
                mm.setFrom(InternetAddress(AccountManager.SENDER_EMAIL))
                //Adding receiver
                mm.addRecipient(
                    Message.RecipientType.TO,
                    InternetAddress(toEmail)
                )
                //Adding subject
                mm.subject = AccountManager.SEND_MAIL_SUBJECT
                //Adding message
                mm.setText(AccountManager.SEND_MAIL_BODY)

                //Sending email
                Transport.send(mm, AccountManager.SENDER_EMAIL, AccountManager.SENDER_PASSWORD)

                appExecutors.mainThread().execute {
                    //Something that should be executed on main thread.
                }

            } catch (e: MessagingException) {
                e.printStackTrace()
            }
        }
    }

}
