package com.example.booking_beacon.repo

import com.example.booking_beacon.constants.CommonConstants
import com.example.booking_beacon.model.user.RegisterNormalUserData
import com.example.booking_beacon.model.Token
import com.example.booking_beacon.model.user.LoginData
import com.example.ktor_client_tutorial.KtorClient
import io.ktor.client.request.post
import io.ktor.http.contentType

class UserRepo {
    private val baseUrl = "${CommonConstants.baseUrl}/auth"

    suspend fun registerNormalUser(registerNormalUserData: RegisterNormalUserData): String{
        return KtorClient.httpClient.post("$baseUrl/join") {
            contentType(io.ktor.http.ContentType.Application.Json)
            body = registerNormalUserData
        }
    }

    suspend fun login(loginData: LoginData): Token{
        return KtorClient.httpClient.post("$baseUrl/login") {
            contentType(io.ktor.http.ContentType.Application.Json)
            body = loginData
        }
    }
}