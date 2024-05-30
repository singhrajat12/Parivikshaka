package com.example.parivikshaka.models

import com.google.gson.annotations.SerializedName

data class TargetResponse(


    @SerializedName("Code") var Code: Int? = null,
    @SerializedName("ListResponce") var ListResponce: Any? = null,
    @SerializedName("Message") var Message: String? = null,
    @SerializedName("SPTLDetailsList") var SPTLDetailsList: Any? = null,
    @SerializedName("SPTLTargetList") var SPTLTargetList: List<SPTLTarget>,
    @SerializedName("Status") var Status: String? = null,

)

data class SPTLTarget(
    @SerializedName("FertilizerTarger") var FertilizerTarger: Int? = null,
    @SerializedName("PesticideTarget") var PesticideTarget: Int? = null,
    @SerializedName("SeedTarget") var SeedTarget: Int? = null,


)