package com.example.parivikshaka.models

import com.google.gson.annotations.SerializedName

data class TargetListRequest(
    @SerializedName("DistrictId") var DistrictId: Int? = null,
    @SerializedName("Password") var Password: String? = null,
    @SerializedName("TypeId") var TypeId: Int? = null,
    @SerializedName("UserName") var UserName: String? = null,

)