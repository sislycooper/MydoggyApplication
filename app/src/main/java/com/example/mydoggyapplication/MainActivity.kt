package com.example.mydoggyapplication

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private var imageView: ImageView? = null
    private var previousUrl: String? = ""
    private var currentUrl: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.photo)
        val retrofit = Retrofit.Builder().baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        apiService = retrofit.create(ApiService::class.java)

        findViewById<View>(R.id.newPhoto).setOnClickListener {
            getRandomImage()
        }
        findViewById<View>(R.id.prevPhoto).setOnClickListener {
            loadPrev()
        }
    }

    private fun getRandomImage() {
        previousUrl = currentUrl
        apiService.getPhoto().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                currentUrl = response.body()?.message
                imageView?.load(currentUrl)
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

            }
        })
    }

    private fun loadPrev() {
        imageView?.load(previousUrl)
    }
}