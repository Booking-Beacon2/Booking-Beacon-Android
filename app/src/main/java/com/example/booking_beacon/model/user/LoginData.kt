package com.example.booking_beacon.model.user

import com.example.booking_beacon.enums.UserType
import kotlinx.serialization.Serializable

@Serializable
data class LoginData(
    val userEmail:String,
    val password:String,
    val userType:UserType
)
