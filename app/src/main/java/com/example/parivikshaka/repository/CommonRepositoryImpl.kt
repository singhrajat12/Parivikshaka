package com.example.parivikshaka.repository

import android.util.Log
import com.example.parivikshaka.SharedPreferenceCommon
import com.example.parivikshaka.db.ApiServices
import com.example.parivikshaka.db.ApiState
import com.example.parivikshaka.db.CommonApiService
import com.example.parivikshaka.db.TokenApiService
import com.example.parivikshaka.db.toResultFlow
import com.example.parivikshaka.models.AuthRequest
import com.example.parivikshaka.models.DistrictList
import com.example.parivikshaka.models.DistrictResponse
import com.example.parivikshaka.models.HobliResponse
import com.example.parivikshaka.models.SampleRequest
import com.example.parivikshaka.models.TalukList
import com.example.parivikshaka.models.TalukResponse
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



    fun SampleFetechData(sampleRequest: SampleRequest) = toResultFlow {
        apiService.GetSample(sampleRequest)
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



    fun getTaluks(username: String, password: String, districtId: Int): Flow<ApiState<TalukResponse>> = toResultFlow {
        apiService.getTaluks(username, password, districtId)
    }

    fun getHobli(username: String, password: String, districtId: Int, talukId: Int): Flow<ApiState<HobliResponse>> = toResultFlow {
        apiService.getHobli(username, password, districtId,talukId)
    }

    fun getDistricts(username: String, password: String): Flow<ApiState<DistrictResponse>> {
        return toResultFlow {
            apiService.getDistricts(username, password)
        }
    }

}
