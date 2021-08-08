package com.fostiums.seaapp.helper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    val BASE_URL = "https://seapp.fostifest.com/api/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun use(): Retrofit {
        return retrofit
    }

}