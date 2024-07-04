package com.aasif.test.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aasif.test.data.Register
import com.aasif.test.databinding.ActivityRegisterBinding
import com.aasif.test.viewModel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var bind: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.loginAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        bind.register.setOnClickListener { register(it) }


    }

    private fun register(view: View) {
        when {
            bind.nameEdit.text.toString().isEmpty() -> bind.nameEdit.error = "Please enter Name"
            bind.phoneEdit.text.toString().isEmpty() -> bind.phoneEdit.error = "Please enter Phone"
            bind.emailEdit.text.toString().isEmpty() -> bind.emailEdit.error = "Please enter Email"
            bind.passwordEdit.text.toString().isEmpty() ->
                bind.passwordEdit.error = "Please enter Password"

            bind.conPasswordEdit.text.toString().isEmpty()
            -> bind.conPasswordEdit.error = "Please enter Confirm Password"

            bind.conPasswordEdit.text.toString() != bind.passwordEdit.text.toString()
            -> bind.conPasswordEdit.error = "Password & Confirm Password not matched"

            else -> {
                bind.register.text = "Creating..."
                bind.register.isEnabled = false

                viewModel.signup(
                    Register(
                        bind.nameEdit.text.toString(),
                        bind.emailEdit.text.toString(),
                        bind.phoneEdit.text.toString(),
                        bind.passwordEdit.text.toString(),
                        "0",
                        "TOKEN12345"
                    ),
                    {

                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()

                    }, {
                        Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
                        bind.register.text = "Register"
                        bind.register.isEnabled = true
                    })

            }


        }


    }

}