package com.example.followerapp

data class Service(
    val service: String,
    val name: String,
    val category: String,
    val rate: String,
    val min: String,
    val max: String,
    val type: String,
    val desc: String,
    val dripfeed: Int
)
