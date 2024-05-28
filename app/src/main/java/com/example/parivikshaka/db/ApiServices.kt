package com.example.parivikshaka.db

import com.example.parivikshaka.models.AuthRequest
import com.example.parivikshaka.models.Sampledata
import com.example.parivikshaka.models.TargetList
import com.example.parivikshaka.models.TargetListRequest
import com.example.parivikshaka.models.TargetRequest
import com.example.parivikshaka.models.TargetResponse
import com.example.parivikshaka.models.User
import com.example.parivikshaka.models.VerifyOtpRequest
import com.example.parivikshaka.models.VerifyOtpResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {


        @POST("http://10.99.238.152:80/KKISANAPI/api/v1/authenticate/user")
        suspend fun login(@Body auth: AuthRequest): Response<User>

        @POST("http://10.99.238.152:80/KKISANAPI/api/v1/authenticate/SendOTPNew")
        suspend fun getVerifyOtpFromServer(@Body request: VerifyOtpRequest): Response<VerifyOtpResponse>

        @GET("Sampledata")
        suspend fun getItems(): List<Sampledata>

//        @GET("")
//        suspend fun fetchCardItems(): List<CardSampleData>

        @POST("http://10.99.238.152:80/KKISANAPI/api/v1/SPTL/getTargetForSPTLSampleCollection")
        suspend fun getTarget(@Body request:TargetRequest): Response<TargetResponse>

        @POST("http://10.99.238.152:80/KKISANAPI/api/v1/SPTL/getDealerDeatilsForSPTL")
        suspend fun getTargetList(@Body request:TargetListRequest): Response<TargetList>

}
