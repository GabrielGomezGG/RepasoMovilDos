package com.example.repasomovildos.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAlgo {

    private val getRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getApi: ApiService = getRetrofit.create(ApiService::class.java)

}