package com.fostiums.seaapp.repository

import android.util.Log
import com.fostiums.seaapp.helper.Retrofit
import com.fostiums.seaapp.models.ProductResponse
import com.fostiums.seaapp.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Product {

    fun getAllProduct(page: Int, onSucces: (ProductResponse?) -> Unit, onError: (Throwable) -> Unit) {

        val apiService: ApiService = Retrofit().retrofit.create(ApiService::class.java)


        apiService.getProductAll(page).enqueue(object :
            Callback<ProductResponse> {

            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                //save token
                onSucces(response.body())
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable){
                onError(t)
                Log.e("REPOSITORY ", "onFailure: ", t)
            }
        })
    }


}