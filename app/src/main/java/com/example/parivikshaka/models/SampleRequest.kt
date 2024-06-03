package com.example.parivikshaka.models

import com.google.gson.annotations.SerializedName


data class SampleRequest(

    @SerializedName("LabRegNo"         ) var LabRegNo: String? = null,
    @SerializedName("FinancialYearID"  ) var FinancialYearID: Int?    = null,
    @SerializedName("DistrictID"       ) var DistrictID: Int?    = null,
    @SerializedName("TalukID"          ) var TalukID: Int?    = null,
    @SerializedName("HobliID"          ) var HobliID: Int?    = null,
    @SerializedName("InspectorName"    ) var InspectorName: String? = null,
    @SerializedName("Designation"      ) var Designation: String? = null,
    @SerializedName("InspectorAddress" ) var InspectorAddress: String? = null,
    @SerializedName("SerialNo"         ) var SerialNo: String? = null,
    @SerializedName("PesticideID"      ) var PesticideID: Int?    = null,
    @SerializedName("FormulationID"    ) var FormulationID: Int?    = null,
    @SerializedName("BatchNo"          ) var BatchNo: String? = null,
    @SerializedName("MfgDate"          ) var MfgDate: String? = null,
    @SerializedName("ExpDate"          ) var ExpDate: String? = null,
    @SerializedName("RegistrationDate" ) var RegistrationDate: String? = null,
    @SerializedName("DesGenPckParel"   ) var DesGenPckParel: String? = null,
    @SerializedName("Lat"              ) var Lat: Double? = null,
    @SerializedName("Lon"              ) var Lon: Double? = null,
    @SerializedName("Accuracy"         ) var Accuracy: Double? = null,
    @SerializedName("UserName"         ) var UserName: String? = null,
    @SerializedName("Password"         ) var Password: String? = null

)

