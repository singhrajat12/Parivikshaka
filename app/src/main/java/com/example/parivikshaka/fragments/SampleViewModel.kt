package com.example.parivikshaka.fragments

import android.util.Log
import javax.inject.Inject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parivikshaka.models.Sampledata
import com.example.parivikshaka.repository.CommonRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch





@HiltViewModel
class SampleViewModel @Inject constructor(private var commonRepositoryImpl: CommonRepositoryImpl) : ViewModel() {

private val _sampleItems = MutableLiveData<List<Sampledata>>()
    val sampleItems: LiveData<List<Sampledata>> get() = _sampleItems

    fun fetchSampleItems(){
        viewModelScope.launch {
            try {
                val items = commonRepositoryImpl.fetchItems()
                _sampleItems.value = items
            }catch (e: Exception){

            }
        }
    }

}


