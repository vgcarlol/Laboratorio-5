package com.example.followerapp

import BalanceResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("add")
    fun addOrder(
        @Query("key") apiKey: String,
        @Query("action") action: String = "add",
        @Query("service") serviceId: String,
        @Query("link") link: String,
        @Query("quantity") quantity: Int
    ): Call<OrderResponse>

    @GET("services")
    fun getServices(
        @Query("key") apiKey: String,
        @Query("action") action: String = "services"
    ): Call<ServicesResponse>

    @GET("balance")
    fun getBalance(
        @Query("key") apiKey: String,
        @Query("action") action: String = "balance"
    ): Call<BalanceResponse>


    companion object {
        private const val BASE_URL = "https://panel.vallague.com/api/v1/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}
