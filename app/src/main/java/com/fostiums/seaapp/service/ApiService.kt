package com.fostiums.seaapp.service


import com.fostiums.seaapp.models.BaseResponse
import com.fostiums.seaapp.models.CredentialResponse
import com.fostiums.seaapp.models.NewProductResponse
import com.fostiums.seaapp.models.ProductResponse
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*


interface ApiService {

    @GET("products")
    fun getProductAll(
        @Query("page") page: Int,
    ): Call<ProductResponse>


    @GET("seller/products")
    fun getProductPenjualAll(
        @Header("Authorization") authHeader: String,
        @Query("page") page: Int,
    ): Call<ProductResponse>


    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<CredentialResponse>

    @DELETE("products/{id}")
    fun deleteProduct(
        @Header("Authorization") authHeader: String,
        @Path(value = "id", encoded = true) id: String
    ): Call<BaseResponse>


    @Multipart
    @POST("products")
    fun tambahProductBaru(
        @Header("Authorization") authHeader: String,
        @Part("kategori") kategori: RequestBody,
        @Part("nama") nama: RequestBody,
        @Part("nutrisi") nutrisi: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part("kadaluwarsa") kadaluwarsa: RequestBody,
        @Part("satuan_harga") satuan_harga: RequestBody,
        @Part foto1: MultipartBody.Part,
        @Part foto2: MultipartBody.Part,
        @Part foto3: MultipartBody.Part,
    ): Call<NewProductResponse>


}
