package com.example.parivikshaka.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parivikshaka.SharedPreferenceCommon
import com.example.parivikshaka.db.ApiState
import com.example.parivikshaka.models.VerifyOtpRequest
import com.example.parivikshaka.models.VerifyOtpResponse
import com.example.parivikshaka.repository.CommonRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//    fun setBearerToken(bearerToken: String) { sharedPreference.setToken(bearerToken)}
@HiltViewModel
class FragmentEnterOTViewModel @Inject constructor(
    private val repositoryImpl: CommonRepositoryImpl,
    private val sharedPreference: SharedPreferenceCommon
) : ViewModel() {

    private val _verificationState = MutableLiveData<ApiState<VerifyOtpResponse>>()
    val verificationState: LiveData<ApiState<VerifyOtpResponse>> = _verificationState

    fun fetchingVerifyOtpApi2(otpRequest: VerifyOtpRequest) = repositoryImpl.getVerifyOtp(otpRequest)
}