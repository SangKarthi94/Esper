package com.sang.esper.data.network


import com.google.gson.GsonBuilder
import com.sang.esper.BuildConfig
import com.sang.esper.data.response.MobileResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("mhrpatel12/esper-assignment/db")
    fun getMobiles(@Body postParam: HashMap<String, String>): Call<MobileResponse>


    companion object RetrofitClient {
        private const val BASE_URL = "https://my-json-server.typicode.com/"

        private val client = OkHttpClient().newBuilder()
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE}).build()
        private val gson = GsonBuilder()
            .setLenient()
            .create()

        fun serviceRequest(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ApiService::class.java)
        }

    }
}