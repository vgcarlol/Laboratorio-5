package com.example.followerapp

data class OrderResponse(
    val status: String,
    val charge: Double,
    val remains: Int,
    val startCount: Int,
    val orderId: Int
)
