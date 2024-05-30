package com.example.parivikshaka.fragments


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parivikshaka.SharedPreferenceCommon
import com.example.parivikshaka.models.AuthRequest
import com.example.parivikshaka.models.User
import com.example.parivikshaka.repository.CommonRepositoryImpl
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
 class OtpLoginViewModel @Inject constructor(private val sharedpreference:SharedPreferenceCommon,private val repositoryImpl: CommonRepositoryImpl) :ViewModel() {
    val mobilenumber: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    fun fetchingOtpApi(otpRequest: AuthRequest) =
        repositoryImpl.getLoginOtp(otpRequest)

    fun getMobileNumber(): LiveData<String> {
        return mobilenumber
    }

    fun savelogin(user: User) {
        val gson = Gson()
        val userJson: String = gson.toJson(user)
        sharedpreference.setauthdata(userJson)
    }
    fun getlogin():User{
        val retrievedJson: String? = sharedpreference.getauthdata()
        val gson = Gson()
            val retrievedUser: User = gson.fromJson(retrievedJson, User::class.java)
        return retrievedUser
            // Use the retrievedUser object as needed


    }




}

