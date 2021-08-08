package com.fostiums.seaapp.service

import com.fostiums.seaapp.models.ProductResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("products")
    fun getProductAll(
        @Query("page") page: Int,
    ): Call<ProductResponse>


}
