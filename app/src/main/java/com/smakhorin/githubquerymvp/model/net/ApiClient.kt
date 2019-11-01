package com.smakhorin.githubquerymvp.model.net

import android.util.Log
import com.google.gson.Gson
import com.smakhorin.githubquerymvp.model.net.entity.JobDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ApiClient {
    private val serviceUrl: String = "https://jobs.github.com/"

    private val gson: Gson = Gson()
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(serviceUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    fun getPositions(type: String, page: Int): List<JobDTO> {
        val response = apiService.getPositions(type,page).execute()
        if (response.isSuccessful) {
                val list = gson.fromJson(response.body(),Array<JobDTO>::class.java).toMutableList()
                return list
        }
        else {
            Log.d("ApiClient",response.errorBody().toString())
            throw IOException(response.errorBody().toString())
        }
    }
}