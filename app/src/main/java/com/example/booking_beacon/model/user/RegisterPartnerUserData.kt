package com.example.booking_beacon.model.user

import kotlinx.serialization.Serializable

@Serializable
data class RegisterPartnerUserData(
    val userName : String,
    val password : String,
    val email : String,
    val phoneNumber:String,
    val partnerName:String,
    val ein:String
)
