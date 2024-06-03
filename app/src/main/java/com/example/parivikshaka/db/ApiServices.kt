package com.example.parivikshaka.db

import com.example.parivikshaka.models.AuthRequest
import com.example.parivikshaka.models.DistrictList
import com.example.parivikshaka.models.DistrictResponse
import com.example.parivikshaka.models.HobliResponse
import com.example.parivikshaka.models.SampleRequest
import com.example.parivikshaka.models.SampleResponse
import com.example.parivikshaka.models.TalukList
import com.example.parivikshaka.models.TalukResponse
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
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {


        @POST("http://10.99.238.152:80/KKISANAPI/api/v1/authenticate/user")
        suspend fun login(@Body auth: AuthRequest): Response<User>

        @POST("http://10.99.238.152:80/KKISANAPI/api/v1/authenticate/SendOTPNew")
        suspend fun getVerifyOtpFromServer(@Body request: VerifyOtpRequest): Response<VerifyOtpResponse>



        @POST("http://10.99.238.152:80/KKISANAPI/api/v1/SPTL/postPesticideRegistartionDetails")
        suspend fun GetSample(@Body sampleRequest: SampleRequest): Response<SampleResponse>



        @POST("http://10.99.238.152:80/KKISANAPI/api/v1/SPTL/getTargetForSPTLSampleCollection")
        suspend fun getTarget(@Body request:TargetRequest): Response<TargetResponse>

        @POST("http://10.99.238.152:80/KKISANAPI/api/v1/SPTL/getDealerDeatilsForSPTL")
        suspend fun getTargetList(@Body request:TargetListRequest): Response<TargetList>


        @GET("http://10.99.238.152:80/KKISANAPI/api/v1/master/districtlist")
        suspend fun getDistricts(
                @Query("Username") username: String,
                @Query("Password") password: String
        ): Response<DistrictResponse>





        @GET("http://10.99.238.152:80/KKISANAPI/api/v1/master/taluklist")
        suspend fun getTaluks(
                @Query("username") username: String,
                @Query("password") password: String,
                @Query("District") districtId: Int
        ): Response<TalukResponse>

        @GET("http://10.99.238.152:80/KKISANAPI/api/v1/master/hoblilist")
        suspend fun getHobli(
                @Query("username") username: String,
                @Query("password") password: String,
                @Query("District") districtId: Int,
                @Query("Taluk") talukId: Int
        ): Response<HobliResponse>

}
