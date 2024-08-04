package com.example.booking_beacon.model

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val accessToken:String,
    val refreshToken:String
)
