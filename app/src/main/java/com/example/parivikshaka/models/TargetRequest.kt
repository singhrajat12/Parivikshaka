package com.example.parivikshaka.models

import com.google.gson.annotations.SerializedName

data class TargetRequest(
    @SerializedName("DistrictId") var DistrictId: Int? = null,
    @SerializedName("FinancialYearID") var FinancialYearID: Int? = null,
    @SerializedName("HobliId") var HobliId: Int? = null,
    @SerializedName("Password") var Password: String? = null,
    @SerializedName("RoleID") var RoleID: Int? = null,
    @SerializedName("TalukId") var TalukId: Int? = null,
    @SerializedName("UserName") var UserName: String? = null,
)


