package com.fostiums.seaapp.repository

import android.content.Context
import android.util.Log
import com.fostiums.seaapp.helper.Retrofit
import com.fostiums.seaapp.helper.TinyDB
import com.fostiums.seaapp.models.BaseResponse
import com.fostiums.seaapp.models.NewProductResponse
import com.fostiums.seaapp.models.ProductResponse
import com.fostiums.seaapp.service.ApiService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File




class Product(context: Context?) {
    var tinyDB: TinyDB = TinyDB(context)
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

    fun deleteProduct(id: Int, onSucces: (BaseResponse?) -> Unit, onError: (Throwable) -> Unit) {

        val apiService: ApiService = Retrofit().retrofit.create(ApiService::class.java)


        apiService.deleteProduct("Bearer "+tinyDB.getString("TOKEN"),id.toString()).enqueue(object :
            Callback<BaseResponse> {

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                //save token
                onSucces(response.body())
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable){
                onError(t)
                Log.e("REPOSITORY ", "onFailure: ", t)
            }
        })
    }

    fun addNewProduct(foto1: String, foto2: String, foto3: String, kategori: String, nama: String, nutrisi: String, harga: String, kadaluwarsa: String, satuan_harga: String,  onSucces: (NewProductResponse?) -> Unit, onError: (Throwable) -> Unit) {


        val file1 = File(foto1)
        val requestFile1: RequestBody = RequestBody.create(MediaType.get("multipart/form-data"), file1)
        val foto1 = MultipartBody.Part.createFormData("foto1", file1.getName(), requestFile1)

        val file2 = File(foto2)
        val requestFile2: RequestBody = RequestBody.create(MediaType.get("multipart/form-data"), file2)
        val foto2 = MultipartBody.Part.createFormData("foto2", file2.getName(), requestFile2)

        val file3 = File(foto3)
        val requestFile3: RequestBody = RequestBody.create(MediaType.get("multipart/form-data"), file3)
        val foto3 = MultipartBody.Part.createFormData("foto3", file3.getName(), requestFile3)



        val kategoriPart: RequestBody =  RequestBody.create(MediaType.get("multipart/form-data"), kategori)
        val namaPart: RequestBody =  RequestBody.create(MediaType.get("multipart/form-data"), nama)
        val nutrisiPart: RequestBody =  RequestBody.create(MediaType.get("multipart/form-data"), nutrisi)
        val hargaPart: RequestBody =  RequestBody.create(MediaType.get("multipart/form-data"), harga)
        val kadaluwarsaPart: RequestBody =  RequestBody.create(MediaType.get("multipart/form-data"), kadaluwarsa)
        val satuan_hargaPart: RequestBody =  RequestBody.create(MediaType.get("multipart/form-data"), satuan_harga)

        val apiService: ApiService = Retrofit().retrofit.create(ApiService::class.java)


        apiService.tambahProductBaru(
            "Bearer "+tinyDB.getString("TOKEN"),
            kategoriPart,
            namaPart,
            nutrisiPart,
            hargaPart,
            kadaluwarsaPart,
            satuan_hargaPart,
            foto1,
            foto2,
            foto3
        ).enqueue(object :
            Callback<NewProductResponse> {

            override fun onResponse(call: Call<NewProductResponse>, response: Response<NewProductResponse>) {
                //save token
                onSucces(response.body())
            }

            override fun onFailure(call: Call<NewProductResponse>, t: Throwable){
                onError(t)
                Log.e("REPOSITORY ", "onFailure: ", t)
            }
        })
    }
}