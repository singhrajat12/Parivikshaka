package com.example.parivikshaka.db

import com.example.parivikshaka.api.Constant
import com.example.parivikshaka.models.AuthRequest
import com.example.parivikshaka.models.Sampledata
import com.example.parivikshaka.models.User
import com.example.parivikshaka.models.VerifyOtpRequest
import com.example.parivikshaka.models.VerifyOtpResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {


        @POST(Constant.login)
        suspend fun login(@Body auth: AuthRequest): Response<User>

        @POST(Constant.VERIFY_OTP)
        suspend fun getVerifyOtpFromServer(@Body request: VerifyOtpRequest): Response<VerifyOtpResponse>

        @GET("Sampledata")
        suspend fun getItems(): List<Sampledata>
}
