package com.example.followerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addOrderButton = findViewById<Button>(R.id.addOrderButton)
        val viewServicesButton = findViewById<Button>(R.id.viewServicesButton)
        val viewBalanceButton = findViewById<Button>(R.id.viewBalanceButton)
        // Inicializar los botones

        addOrderButton.setOnClickListener {
            val intent = Intent(this, AddOrderActivity::class.java)
            startActivity(intent)
        }

        viewServicesButton.setOnClickListener {
            val intent = Intent(this, ServicesActivity::class.java)
            startActivity(intent)
        }

        viewBalanceButton.setOnClickListener {
            val intent = Intent(this, BalanceActivity::class.java)
            startActivity(intent)
        }
    }
}
