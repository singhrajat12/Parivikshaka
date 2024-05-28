package com.example.parivikshaka.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parivikshaka.SharedPreferenceCommon
import com.example.parivikshaka.db.ApiState
import com.example.parivikshaka.models.TargetListRequest
import com.example.parivikshaka.models.TargetRequest
import com.example.parivikshaka.models.TargetResponse
import com.example.parivikshaka.models.VerifyOtpRequest
import com.example.parivikshaka.models.VerifyOtpResponse
import com.example.parivikshaka.repository.CommonRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject





@HiltViewModel
class TargetViewmodel @Inject constructor(
    private val repositoryImpl: CommonRepositoryImpl,
) : ViewModel() {

    private val _verificationState = MutableLiveData<ApiState<TargetResponse>>()
    val verificationState: LiveData<ApiState<TargetResponse>> = _verificationState

    fun fetchingTargetApi(TargetRequest: TargetRequest) = repositoryImpl.getTargetData(TargetRequest)

    fun fetchingTargetListApi(TargetListRequest: TargetListRequest) = repositoryImpl.getTargetListData(TargetListRequest)
}