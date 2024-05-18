package com.example.parivikshaka.models

import com.google.gson.annotations.SerializedName

data class VerifyOtpRequest(
    @SerializedName("UserName") var UserName: String? = null,
    @SerializedName("Password") var Password: String? = null,
    @SerializedName("MobileNo") var MobileNo: String? = null,
    @SerializedName("UserId") var UserId: String? = null,

)