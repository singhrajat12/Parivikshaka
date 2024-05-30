package com.example.parivikshaka.models

import com.google.gson.annotations.SerializedName

data class TargetList(

    @SerializedName("SPTLDetailsList" ) var SPTLDetailsList : ArrayList<SPTLDetailsList> = arrayListOf(),
    @SerializedName("SPTLTargetList"  ) var SPTLTargetList  : String?                    = null,
    @SerializedName("Status"          ) var Status          : String?                    = null,
    @SerializedName("Message"         ) var Message         : String?                    = null,
    @SerializedName("Code"            ) var Code            : Int?                       = null,
    @SerializedName("ListResponce"    ) var ListResponce    : String?                    = null


)

data class SPTLDetailsList(

    @SerializedName("LabRegNo"                ) var LabRegNo                : String? = null,
    @SerializedName("FinancialYearID"         ) var FinancialYearID         : Int?    = null,
    @SerializedName("FinancialYearName"       ) var FinancialYearName       : String? = null,
    @SerializedName("DistrictID"              ) var DistrictID              : Int?    = null,
    @SerializedName("DistrictNameEng"         ) var DistrictNameEng         : String? = null,
    @SerializedName("TalukID"                 ) var TalukID                 : Int?    = null,
    @SerializedName("TalukNameEng"            ) var TalukNameEng            : String? = null,
    @SerializedName("HobliID"                 ) var HobliID                 : Int?    = null,
    @SerializedName("HobliNameEng"            ) var HobliNameEng            : String? = null,
    @SerializedName("InspectorName"           ) var InspectorName           : String? = null,
    @SerializedName("Designation"             ) var Designation             : String? = null,
    @SerializedName("InspectorAddress"        ) var InspectorAddress        : String? = null,
    @SerializedName("SerialNo"                ) var SerialNo                : String? = null,
    @SerializedName("PesticideID"             ) var PesticideID             : Int?    = null,
    @SerializedName("PesticideName"           ) var PesticideName           : String? = null,
    @SerializedName("FormulationID"           ) var FormulationID           : Int?    = null,
    @SerializedName("Formulation"             ) var Formulation             : String? = null,
    @SerializedName("BatchNo"                 ) var BatchNo                 : String? = null,
    @SerializedName("MfgDate"                 ) var MfgDate                 : String? = null,
    @SerializedName("ExpDate"                 ) var ExpDate                 : String? = null,
    @SerializedName("RegistrationDate"        ) var RegistrationDate        : String? = null,
    @SerializedName("DesGenPckParel"          ) var DesGenPckParel          : String? = null,
    @SerializedName("LabID"                   ) var LabID                   : String? = null,
    @SerializedName("TestType"                ) var TestType                : String? = null,
    @SerializedName("SecurelyPacked"          ) var SecurelyPacked          : String? = null,
    @SerializedName("OuterSealed"             ) var OuterSealed             : String? = null,
    @SerializedName("SealedSample"            ) var SealedSample            : String? = null,
    @SerializedName("BothSealed"              ) var BothSealed              : String? = null,
    @SerializedName("UnbrokenSealed"          ) var UnbrokenSealed          : String? = null,
    @SerializedName("TallingwithSpecimenSeal" ) var TallingwithSpecimenSeal : String? = null,
    @SerializedName("AnalysisFit"             ) var AnalysisFit             : String? = null,
    @SerializedName("FirstCode"               ) var FirstCode               : Int?    = null,
    @SerializedName("SecondCode"              ) var SecondCode              : Int?    = null,
    @SerializedName("Lat"                     ) var Lat                     : Int?    = null,
    @SerializedName("Lon"                     ) var Lon                     : Int?    = null,
    @SerializedName("Accuracy"                ) var Accuracy                : Int?    = null,
    @SerializedName("UserName"                ) var UserName                : String? = null,
    @SerializedName("Password"                ) var Password                : String? = null,
    @SerializedName("RegNo"                   ) var RegNo                   : String? = null,
    @SerializedName("ApplicationName"         ) var ApplicationName         : String? = null,
    @SerializedName("ApplicationConcern"      ) var ApplicationConcern      : String? = null,
    @SerializedName("Address"                 ) var Address                 : String? = null


)