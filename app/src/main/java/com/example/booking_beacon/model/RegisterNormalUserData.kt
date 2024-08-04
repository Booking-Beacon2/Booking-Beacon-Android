package com.example.booking_beacon.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterNormalUserData(
    val userName : String,
    val password : String,
    val email : String,
    val phoneNumber:String
)
