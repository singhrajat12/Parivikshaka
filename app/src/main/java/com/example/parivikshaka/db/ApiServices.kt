package com.example.parivikshaka.db

import com.example.parivikshaka.api.Constant
import com.example.parivikshaka.models.AuthRequest
import com.example.parivikshaka.models.Sampledata
import com.example.parivikshaka.models.User

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {


        @POST(Constant.login)
        suspend fun login(@Body auth: AuthRequest): Response<User>

        @GET("Sampledata")
        suspend fun getItems(): List<Sampledata>
}
