package com.example.prjgrannyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class frmRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frm_register)

        val txtRegUsername : EditText = findViewById(R.id.txtRegUsername)
        val txtRegPassword : EditText = findViewById(R.id.txtRegPassword)
        val txtRegConfirmPassword : EditText = findViewById(R.id.txtRegConfirmPassword)

        val btnRegSignUp : Button = findViewById(R.id.btnRegSignUp)
        btnRegSignUp.setOnClickListener {
            var check: Boolean = false
            for (i in arrUsers.indices) {
                if (txtRegUsername.text.toString() == arrUsers[i].name) {
                    txtRegUsername.error="User already exists"
                    check = true
                    break
                }
            }
            if (!check) {
                if (txtRegPassword.text.toString() == txtRegConfirmPassword.text.toString() && txtRegUsername.text.length >= 1 && txtRegPassword.text.length >= 1) {
                    val user = User(txtRegUsername.text.toString(), txtRegPassword.text.toString(), "")
                    arrUsers.add(user)
                    Toast.makeText(this, "Registered user: " + txtRegUsername.text.toString(), Toast.LENGTH_SHORT).show()
                }
                else {
                    txtRegUsername.error="This needs to be filled in"
                    txtRegPassword.error="This needs to be filled in"
                    txtRegConfirmPassword.error="This needs to be filled in"
                }
            }
        }
        val btnMainMenu : Button = findViewById(R.id.btnMainMenu)
        btnMainMenu.setOnClickListener {
            finish()
        }
    }
}