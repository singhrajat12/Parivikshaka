package com.example.parivikshaka.fragments


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parivikshaka.models.AuthRequest
import com.example.parivikshaka.repository.CommonRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
 class OtpLoginViewModel @Inject constructor(private val repositoryImpl: CommonRepositoryImpl) :ViewModel() {
    val mobilenumber: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    fun fetchingOtpApi(otpRequest: AuthRequest) =
        repositoryImpl.getLoginOtp(otpRequest)

    fun getMobileNumber(): LiveData<String> {
        return mobilenumber
    }



}

