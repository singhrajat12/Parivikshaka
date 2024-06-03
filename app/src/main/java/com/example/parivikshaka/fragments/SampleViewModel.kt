package com.example.parivikshaka.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import com.example.parivikshaka.repository.CommonRepositoryImpl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.parivikshaka.R
import com.example.parivikshaka.db.ApiState
import com.example.parivikshaka.models.AuthRequest
import com.example.parivikshaka.models.DistrictList
import com.example.parivikshaka.models.DistrictResponse
import com.example.parivikshaka.models.HobliList
import com.example.parivikshaka.models.SampleRequest
import com.example.parivikshaka.models.TalukList
import com.example.parivikshaka.models.TalukResponse
import com.example.parivikshaka.models.User
import com.example.parivikshaka.utils.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch


@HiltViewModel
class SampleViewModel @Inject constructor(
    private val repositoryImpl: CommonRepositoryImpl
) : ViewModel() {

    private val _districtList = MutableLiveData<ArrayList<DistrictList>>()
    val districtList: LiveData<ArrayList<DistrictList>> = _districtList



    private val _talukList = MutableLiveData<List<TalukList>>()
    val talukList: LiveData<List<TalukList>> = _talukList



    private val _hobliList = MutableLiveData<List<HobliList>>()
    val hobliList: LiveData<List<HobliList>> = _hobliList


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    var LabRegNo = ObservableField("")
    var FinancialYearID = ObservableField("")
    var DistrictID = ObservableField("")
    var TalukID = ObservableField("")
    var HobliID = ObservableField("")
    var InspectorName = ObservableField("")
    var Designation = ObservableField("")
    var InspectorAddress = ObservableField("")
    var SerialNo = ObservableField("")
    var PesticideID = ObservableField("")
    var FormulationID = ObservableField("")
    var BatchNo = ObservableField("")

    var MfgDate = ObservableField("")
    var ExpDate = ObservableField("")
    var RegistrationDate = ObservableField("")
    var DesGenPckParel = ObservableField("")
    var Lat = ObservableField("")
    var Lon = ObservableField("")
    var Accuracy = ObservableField("")
    var UserName = ObservableField("")
    var Password = ObservableField("")


    private var mContext: Context? = null
    fun getContext(context: Context) {
        mContext = context
    }

    @SuppressLint("ResourceAsColor")
    fun onClick(view: View) {
        when (view.id) {
            R.id.submit_btn -> {
                when {
                    LabRegNo.get()?.isEmpty() == true -> {
                        view.context.showToast("Please enter LabRegNo")
                    }

                    TalukID.get()?.isEmpty() == true -> {
                        view.context.showToast("Please enter TalukID")
                    }


                    InspectorName.get()?.isEmpty() == true -> {
                        view.context.showToast("Please InspectorName")
                    }

                    FinancialYearID.get()?.isEmpty() == true -> {
                        view.context.showToast("Please enter FinancialYearID")

                    }


                    Designation.get()?.isEmpty() == true -> {
                        view.context.showToast("Please enter Designation")

                    }

                    InspectorAddress.get()?.isEmpty() == true -> {
                        view.context.showToast("Please enter InspectorAddress")

                    }

                    SerialNo.get()?.isEmpty() == true -> {
                        view.context.showToast("Please enter SerialNo")
                    }


                    BatchNo.get()?.isEmpty() == true -> {
                        view.context.showToast("Please enter BatchNo")

                    }

                    MfgDate.get()?.isEmpty() == true -> {
                        view.context.showToast("Please enter MfgDate")
                    }


                    ExpDate.get()?.isEmpty() == true -> {
                        view.context.showToast("Please enter ExpDate")
                    }

                    RegistrationDate.get()?.isEmpty() == true -> {
                        view.context.showToast("Please enter RegistrationDate")

                    }

                    DesGenPckParel.get()?.isEmpty() == true -> {
                        view.context.showToast("Please enter DesGenPckParel")

                    }

                    UserName.get()?.isEmpty() == true -> {
                        view.context.showToast("Please enter UserName")

                    }

                    Password.get()?.isEmpty() == true -> {
                        view.context.showToast("Please enter Password")
                    }

                    else -> {


                        val accuracyString = Accuracy.get()
                        val accuracy = accuracyString?.toDoubleOrNull()

                        if (accuracy == null) {
                            view.context.showToast("Please enter a valid Accuracy value")
                            return
                        }

                        val latString = Lat.get()
                        val lat = latString?.toDoubleOrNull()

                        if (lat == null) {
                            view.context.showToast("Please enter a valid Lat value")
                            return
                        }

                        val lonString = Lon.get()
                        val lon = lonString?.toDoubleOrNull()

                        if (lon == null) {
                            view.context.showToast("Please enter a valid lon value")
                            return
                        }
                        val FormulationString = FormulationID.get()
                        val formulationid = FormulationString?.toIntOrNull()

                        if (formulationid == null) {
                            view.context.showToast("Please enter a valid formulationid ")
                            return
                        }


                        val FinancialYearIDString = FinancialYearID.get()
                        val financialYearID = FinancialYearIDString?.toIntOrNull()

                        if (financialYearID == null) {
                            view.context.showToast("Please enter a valid financialYearID ")
                            return
                        }

                        val DistrictString = DistrictID.get()
                        val districtID = DistrictString?.toIntOrNull()

                        if (districtID == null) {
                            view.context.showToast("Please enter a valid financialYearID ")
                            return
                        }

                        val TalukString = TalukID.get()
                        val talukID = TalukString?.toIntOrNull()

                        if (talukID == null) {
                            view.context.showToast("Please enter a valid financialYearID ")
                            return
                        }

                        val HobliString = HobliID.get()
                        val hobliID = HobliString?.toIntOrNull()

                        if (hobliID == null) {
                            view.context.showToast("Please enter a valid financialYearID ")
                            return
                        }

                        val PesticideIDString = PesticideID.get()
                        val pesticideID = PesticideIDString?.toIntOrNull()

                        if (pesticideID == null) {
                            view.context.showToast("Please enter a valid financialYearID ")
                            return
                        }

                        var request = SampleRequest(
                            UserName = "AgriDept",
                            Password = "AD@432!",
                            Accuracy = accuracy,
                            Lat = lat,
                            Lon = lon,
                            DesGenPckParel = this@SampleViewModel.DesGenPckParel.get(),
                            RegistrationDate = this@SampleViewModel.RegistrationDate.get(),
                            ExpDate = this@SampleViewModel.ExpDate.get(),
                            MfgDate = this@SampleViewModel.MfgDate.get(),
                            BatchNo = this@SampleViewModel.BatchNo.get(),
                            FormulationID = formulationid,
                            LabRegNo = this@SampleViewModel.LabRegNo.get(),
                            FinancialYearID = financialYearID,
                            DistrictID = districtID,
                            TalukID = talukID,
                            HobliID = hobliID,
                            InspectorAddress = this@SampleViewModel.InspectorAddress.get(),
                            InspectorName = this@SampleViewModel.InspectorName.get(),
                            Designation = this@SampleViewModel.Designation.get(),
                            SerialNo = this@SampleViewModel.SerialNo.get(),
                            PesticideID = pesticideID


                        )
                        getSample(view, request)
                        Log.d("inventVendor1", "$request")

                    }

                }
            }
        }
    }

    private fun getSample(view: View, request: SampleRequest) {
        viewModelScope.launch {
            repositoryImpl.SampleFetechData(request).collect {
                when (it) {
                    is ApiState.Loading -> {}
                    is ApiState.Success -> {
                        Log.d("inventVendor", "$it")
                        Toast.makeText(view.context, "Request Submitted", Toast.LENGTH_LONG).show()
                    }

                    is ApiState.Failure -> {
                        view.context.showToast("${it.msg}")
                    }

                    is ApiState.Error -> {

                    }
                }
            }
        }
    }


    fun fetchDistricts(username: String, password: String) {
        viewModelScope.launch {
            repositoryImpl.getDistricts(username, password).collect { state ->
                when (state) {
                    is ApiState.Loading -> {
                        Log.d("SampleViewModel", "Loading districts")
                    }
                    is ApiState.Success -> {
                        Log.d("SampleViewModel", "Districts fetched successfully: ${state.data}")
                        _districtList.value = state.data.DistrictList
                    }
                    is ApiState.Error -> {
                        val errorMessage = "Error fetching districts: ${state.errorCode}"
                        Log.e("SampleViewModel", errorMessage)
                        _error.value = state.errorCode.toString()
                    }
                    is ApiState.Failure -> {
                        val errorMessage = "Failure fetching districts: ${state.msg}"
                        Log.e("SampleViewModel", errorMessage)
                        _error.value = state.msg
                    }
                    else -> {
                        Log.e("SampleViewModel", "Unhandled state: $state")
                    }
                }
            }
        }
    }


    fun fetchTaluks(username: String, password: String, districtId: Int) {
        viewModelScope.launch {
            repositoryImpl.getTaluks(username, password, districtId).collect { state ->
                when (state) {
                    is ApiState.Success -> _talukList.value = state.data.TalukList
                    is ApiState.Error -> _error.value = state.errorCode.toString()
                    else -> {}
                }
            }
        }
    }



    fun fetchHobli(username: String, password: String, districtId: Int, talukId: Int){
        viewModelScope.launch {
            repositoryImpl.getHobli(username,password,districtId,talukId).collect{state ->
                when(state){
                    is ApiState.Success -> _hobliList.value = state.data.HobliList
                    is ApiState.Error -> _error.value = state.errorCode.toString()
                    else ->{

                    }
                }
            }
        }
    }



    // Assuming you have a method to get login details, expose the DistrictId
//    private val _loginData = MutableLiveData<User>()
//    val loginData: LiveData<User> = _loginData
//
//    fun setAuthRequest(authRequest: AuthRequest) {
//        viewModelScope.launch {
//            repositoryImpl.getLoginOtp(authRequest).collect { state ->
//                when (state) {
//                    is ApiState.Success -> _loginData.value = state.data
//                    is ApiState.Error -> _error.value = state.errorCode.toString()
//                    else -> {}
//                }
//            }
//        }
//    }
}














