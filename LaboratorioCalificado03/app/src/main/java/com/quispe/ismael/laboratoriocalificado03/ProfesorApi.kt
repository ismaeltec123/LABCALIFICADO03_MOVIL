package com.quispe.ismael.laboratoriocalificado03


import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProfesorApi {
    @GET("/list/teacher")
    suspend fun getProfesores(): Response<ProfesorResponse>

    companion object {
        fun create(): ProfesorApi {
            val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(newRequest)
            }

            val client = httpClient.build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://private-effe28-tecsup1.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ProfesorApi::class.java)
        }
    }
}