package com.example.parivikshaka.models

import com.google.gson.annotations.SerializedName

class VerifyOtpResponse (
    @SerializedName("OTP") var OTP : String? = null,
    @SerializedName("UserID") var UserID : String? = null,
    @SerializedName("Status") var Status : String? = null,
    @SerializedName("Message") var Message : String? = null,
    @SerializedName("Code") var Code : String? = null,

)