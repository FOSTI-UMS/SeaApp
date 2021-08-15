package com.fostiums.seaapp.repository

import android.content.Context
import android.util.Log
import com.fostiums.seaapp.helper.Retrofit
import com.fostiums.seaapp.helper.TinyDB
import com.fostiums.seaapp.models.ProductResponse
import com.fostiums.seaapp.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Penjual(context: Context?) {
    var tinyDB: TinyDB = TinyDB(context)

    fun getAllProduct(page: Int, onSucces: (ProductResponse?) -> Unit, onError: (Throwable) -> Unit) {

        val apiService: ApiService = Retrofit().retrofit.create(ApiService::class.java)


        apiService.getProductPenjualAll("Bearer "+tinyDB.getString("TOKEN"), page).enqueue(object :
            Callback<ProductResponse> {

            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                //save token
                onSucces(response.body())
                Log.e("REPOSITORY SUCCESS ", response.body().toString())
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable){
                onError(t)
                Log.e("REPOSITORY ", "onFailure: ", t)
            }
        })
    }
}