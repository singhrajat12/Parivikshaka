package com.example.parivikshaka.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("Code")  var Code: Int? = null,
    @SerializedName("Message") var Message: String? = null,
    @SerializedName("Status") var Status: String? = null,
    @SerializedName("Token")  var Token: String? = null,
    @SerializedName("District")  var District: Int? = null,
    @SerializedName("Taluk") var Taluk: Int? = null,
    @SerializedName("Hobli")  var Hobli: Int? = null,
    @SerializedName("UserId") var UserId: String? = null,
    @SerializedName("UserName")  var UserName: String? = null,
    @SerializedName("RoleName")  var RoleName: String? = null,
    @SerializedName("RoleId") var RoleId: Int? = null
)