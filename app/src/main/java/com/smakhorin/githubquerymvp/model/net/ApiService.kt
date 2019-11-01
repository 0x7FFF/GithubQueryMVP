package com.smakhorin.githubquerymvp.model.net

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("positions.json")
    fun getPositions(@Query("search") type: String, @Query("page") page:Int): Call<JsonArray>
}