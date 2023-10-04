package com.example.followerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)

        val sendOrderButton: Button = findViewById(R.id.sendOrderButton)
        val quantityEditText: EditText = findViewById(R.id.quantityEditText)
        val linkEditText: EditText = findViewById(R.id.linkEditText)

        val apiService = ApiService.create()

        sendOrderButton.setOnClickListener {
            val quantity = quantityEditText.text.toString().toIntOrNull()
            val link = linkEditText.text.toString()

            if (quantity != null && link.isNotEmpty()) {
                apiService.addOrder(
                    apiKey = "4xvOG3fT0FNKZ9878UvczdvZ2RG2vS67",
                    action = "add",
                    serviceId = "7805",
                    link = link,
                    quantity = quantity
                ).enqueue(object: Callback<OrderResponse> {
                    override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                        if(response.isSuccessful) {
                            val orderResponse = response.body()
                            Toast.makeText(this@AddOrderActivity, "Order Added Successfully! Response: $orderResponse", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this@AddOrderActivity, "Failed to add order! Response Code: ${response.code()}", Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                        Toast.makeText(this@AddOrderActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }
}
