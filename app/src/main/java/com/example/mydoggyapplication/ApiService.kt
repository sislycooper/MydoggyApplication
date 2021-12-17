package com.example.mydoggyapplication

import retrofit2.Call
import retrofit2.http.GET

public interface ApiService {
    @GET("breeds/image/random")
    fun getPhoto(): Call<ApiResponse>
}