package com.example.parivikshaka.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthRequest(
    @SerializedName("Username") var Username: String? = null,
    @SerializedName("Password")  var Password: String? = null,
    @SerializedName("AuthUsername")  var AuthUsername: String? = null,
    @SerializedName("AuthPassword")var AuthPassword: String? = null
)
