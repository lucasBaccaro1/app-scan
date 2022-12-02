package com.gob.scan_app.repository

import com.gob.scan_app.application.Constants
import com.gob.scan_app.data.model.LoginResponse
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("precheck/api/appMovil/auth?")
    suspend fun getLogin(@Query("username") username : String, @Query("password") password:String) : LoginResponse
}


object RetrofitClient {

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_LICTA)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}