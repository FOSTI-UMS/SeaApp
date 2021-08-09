package com.fostiums.seaapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.fostiums.seaapp.R
import com.fostiums.seaapp.helper.TinyDB
import com.fostiums.seaapp.repository.Auth


class Login : AppCompatActivity() {

    lateinit var txtUsername: EditText
    lateinit var txtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var progressLoadingLogin: ProgressBar
    lateinit var tinyDB : TinyDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tinyDB = TinyDB(this)
        // initialize UI
        txtUsername = findViewById(R.id.editTextEmail)
        txtPassword = findViewById(R.id.editTextPassword)
        btnLogin = findViewById(R.id.button_login)
        progressLoadingLogin = findViewById(R.id.progressBarLogin)

        // ui action
        btnLogin.setOnClickListener {
            goLogin()
        }



    }

    fun loadingLoginProgress(isLoading: Boolean){
        if (isLoading){
            btnLogin.visibility = View.GONE
            progressLoadingLogin.visibility = View.VISIBLE
        }else{
            btnLogin.visibility = View.VISIBLE
            progressLoadingLogin.visibility = View.GONE
        }
    }

    fun goLogin() {

        loadingLoginProgress(true)
        Auth().login(
            txtUsername.text.toString(),
            txtPassword.text.toString(),
            {
                data ->
                    loadingLoginProgress(false)
                    //save credential

                    if (data != null) {
                        if (data.error == false) {
                            if (data != null) {
                                tinyDB.putString("TOKEN", data.data.token)
                                tinyDB.putInt("ROLE", data.data.user_data.role)
                                tinyDB.putObject("USER_DATA",data.data.user_data)
                                onBackPressed()
                            } else {
                                SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Gagal")
                                    .setContentText("Cek Username/password")
                                    .show()
                            }
                        }
                    }else{
                        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Gagal")
                            .setContentText("Cek Username/password")
                            .show()
                    }

            },
            {
                error ->
                    loadingLoginProgress(false)
                    SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Gagal")
                        .setContentText("Cek Username/password")
                        .show()
            }

        )
    }
}