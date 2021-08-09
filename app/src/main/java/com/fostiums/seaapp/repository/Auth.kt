package com.fostiums.seaapp.repository

import android.util.Log
import com.fostiums.seaapp.helper.Retrofit
import com.fostiums.seaapp.models.CredentialResponse
import com.fostiums.seaapp.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Auth {

    fun login(email: String, password: String, onSucces: (CredentialResponse?) -> Unit, onError: (Throwable) -> Unit) {

        val apiService: ApiService = Retrofit().retrofit.create(ApiService::class.java)


        apiService.login(email, password).enqueue(object :
            Callback<CredentialResponse> {

            override fun onResponse(call: Call<CredentialResponse>, response: Response<CredentialResponse>) {
                //save token
                onSucces(response.body())
            }

            override fun onFailure(call: Call<CredentialResponse>, t: Throwable){
                onError(t)
                Log.e("REPOSITORY ", "onFailure: ", t)
            }
        })
    }


}