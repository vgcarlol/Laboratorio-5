package com.example.followerapp

import BalanceResponse
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.followerapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BalanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance)

        val apiService = ApiService.create()
        val balanceTextView: TextView = findViewById(R.id.balanceTextView) // Asegúrate de tener un TextView con este ID en tu layout

        apiService.getBalance("4xvOG3fT0FNKZ9878UvczdvZ2RG2vS67").enqueue(object : Callback<BalanceResponse> {
            override fun onResponse(call: Call<BalanceResponse>, response: Response<BalanceResponse>) {
                if (response.isSuccessful) {
                    val balance = response.body()?.balance ?: "Unknown"
                    balanceTextView.text = "Balance: $balance ${response.body()?.currency}"
                    // Log para verificar la respuesta de la API
                    Log.d("API_RESPONSE", "Success: $balance ${response.body()?.currency}")
                } else {
                    Toast.makeText(this@BalanceActivity, "Failed to retrieve balance", Toast.LENGTH_SHORT).show()
                    // Log para verificar el código de error y el mensaje de error
                    Log.d("API_RESPONSE", "Failed: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BalanceResponse>, t: Throwable) {
                Toast.makeText(this@BalanceActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                // Log para verificar detalles del error
                Log.d("API_ERROR", "Error: ${t.message}")
            }
        })
    }
}
