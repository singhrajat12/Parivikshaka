package com.example.parivikshaka.db


sealed class ApiState<out T> {


    data class Success<out R>(val data: R) : ApiState<R>()
    data class Failure(val msg:String) : ApiState<Nothing>()
    data class Error(val errorCode: Int) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()

}