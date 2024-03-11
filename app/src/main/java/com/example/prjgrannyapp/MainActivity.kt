package com.example.prjgrannyapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

var arrUsers = ArrayList<User>()
var SignedIndex : Int = -1

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        val usernameArray = arrayOf("Granny", "Granpa", "Timmy", "Mom")
        val passwordArray = arrayOf("Timmy", "John", "1234", "4321")

         */
        //Another way to do arrays
        val user1 = User("Granny", "Timmy", "https://picsum.photos/id/100/200/300")
        val user2 = User("Timmy", "1234", "https://picsum.photos/id/54/200/300")
        arrUsers.add(user1)
        arrUsers.add(user2)

        val txtInputUsername : EditText = findViewById(R.id.txtUsername)
        val txtInputPassword : EditText = findViewById(R.id.txtPassword)
        val imageView : ImageView = findViewById(R.id.imageView)

        val btnPress : Button = findViewById(R.id.btnSignIn)
        btnPress.setOnClickListener {
            //var count = 0
            var check : Boolean = false
            SignedIndex = -1
            for (i in arrUsers.indices){
                if (txtInputUsername.text.toString() == arrUsers[i].name && arrUsers[i].password == txtInputPassword.text.toString()){
                    Toast.makeText(this, "Logged in as " + arrUsers[i].name, Toast.LENGTH_SHORT).show()
                    check = true
                    SignedIndex = i
                    val intent = Intent(this, Welcome::class.java)
                    startActivity(intent)
                    break
                }
            }

            /*
            while (count < usernameArray.size){
                if (usernameArray[count] == txtInputUsername.text.toString() && passwordArray[count] == txtInputPassword.text.toString()){
                    Toast.makeText(this, "Logged in as " + usernameArray[count], Toast.LENGTH_SHORT).show()
                    check = true
                    break
                }
                count++
            }

             */
            if (!check){
                Toast.makeText(this, "Imposter", Toast.LENGTH_SHORT).show()
            }
        }
        val btnSignUp : Button = findViewById(R.id.btnRegister)
        btnSignUp.setOnClickListener {
            val intent = Intent(this, frmRegister::class.java)
            startActivity(intent)
        }
    }
}