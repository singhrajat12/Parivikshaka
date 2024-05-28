package com.example.parivikshaka.repository

import com.example.parivikshaka.SharedPreferenceCommon
import com.example.parivikshaka.db.ApiServices
import com.example.parivikshaka.db.ApiState
import com.example.parivikshaka.db.CommonApiService
import com.example.parivikshaka.db.TokenApiService
import com.example.parivikshaka.db.toResultFlow
import com.example.parivikshaka.models.AuthRequest

import com.example.parivikshaka.models.Sampledata
import com.example.parivikshaka.models.TargetListRequest
import com.example.parivikshaka.models.TargetRequest
import com.example.parivikshaka.models.User
import com.example.parivikshaka.models.VerifyOtpRequest
//import com.example.parivikshaka.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(
    @TokenApiService private val withToken: ApiServices,
    @CommonApiService private val apiService: ApiServices,
    private val sharedPreferenceCommon: SharedPreferenceCommon
) {
    val token = sharedPreferenceCommon.gettoken()





    fun getLoginOtp(authRequest: AuthRequest): Flow<ApiState<User>> = toResultFlow {
        apiService.login(authRequest)
    }

    fun getVerifyOtp(verifyRequest: VerifyOtpRequest) = toResultFlow {
        apiService.getVerifyOtpFromServer(verifyRequest)
    }



    suspend fun fetchItems(): List<Sampledata>{
        return apiService.getItems()

    }

//    suspend fun fetchCardItem(): List<CardSampleData>{
//        return apiService.fetchCardItems()
//    }

    fun getTargetData(verifyTarget: TargetRequest) = toResultFlow {
        apiService.getTarget(verifyTarget)
    }

    fun getTargetListData(verifyTarget: TargetListRequest) = toResultFlow {
        apiService.getTargetList(verifyTarget)
    }



}
