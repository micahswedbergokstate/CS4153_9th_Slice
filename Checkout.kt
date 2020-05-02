package com.example.cs4153pizzaapp

//Imports
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

        //Executors for the mail functions
        appExecutors = AppExecutors()

        btnConfirmOrder.setOnClickListener {

            //Either checkout success, or checkout failure
            var success = true
            if (success) {
                if (AccountManager.getCurrentUserType() == AccountManager.UserType.USER)
                sendEmail(AccountManager.getUser()) //Send email if checkout is successful
                val intent = Intent(this, OrderConfirmation::class.java)
                startActivity(intent)
            } else {
                //Order Failure
            }

        }
    }

    //Function to send email
    private fun sendEmail(toEmail: String) {
        appExecutors.diskIO().execute {
            val props = System.getProperties()
            //Set host name: gmail.com
            props.put("mail.smtp.host", "smtp.gmail.com")
            //Set port number for socket factory
            props.put("mail.smtp.socketFactory.port", "465")
            //Create sockets for the mail client
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            //Authenticate
            props.put("mail.smtp.auth", "true")
            //Set port for mail client
            props.put("mail.smtp.port", "465")

            val session = Session.getInstance(props)

            try {
                //Creating MimeMessage object
                //Multipurpose Internet Mail Extensions
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
