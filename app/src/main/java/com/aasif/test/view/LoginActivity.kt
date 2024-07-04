package com.aasif.test.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.aasif.test.viewModel.LoginViewModel
import com.aasif.test.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {


    private lateinit var bind: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)


        bind.login.setOnClickListener { view ->

            when {
                bind.emailEdit.text.toString()
                    .isEmpty() -> bind.emailEdit.error = "Please enter email"

                bind.passwordEdit.text.toString()
                    .isEmpty() -> bind.passwordEdit.error = "Please enter password"

                else -> {
                    bind.login.text = "Logging..."
                    bind.login.isEnabled = false

                    viewModel.login(
                        bind.emailEdit.text.toString(),
                        bind.passwordEdit.text.toString(),
                        {
                            startActivity(Intent(this, HomeActivity::class.java))
                            finish()

                        }, {
                            Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
                            bind.login.text = "Login"
                            bind.login.isEnabled = true
                        })

                }
            }

        }


        bind.createAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

    }


}