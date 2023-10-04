package com.example.followerapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)

        val apiService = ApiService.create()
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = findViewById<RecyclerView>(R.id.servicesRecyclerView)

        progressBar.visibility = View.VISIBLE

        apiService.getServices("your_api_key", "services").enqueue(object : Callback<ServicesResponse> {
            override fun onResponse(call: Call<ServicesResponse>, response: Response<ServicesResponse>) {
                Log.d("API_RESPONSE", "Response: ${response.body()}")
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val services = response.body()?.services
                    recyclerView.adapter = ServicesAdapter(services ?: emptyList())
                } else {
                    Toast.makeText(this@ServicesActivity, "Error loading services", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ServicesResponse>, t: Throwable) {
                Log.e("API_ERROR", "Error: ${t.message}", t)
                progressBar.visibility = View.GONE
                Toast.makeText(this@ServicesActivity, "Failed to load services", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
