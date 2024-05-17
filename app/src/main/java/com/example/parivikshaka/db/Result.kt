package com.example.parivikshaka.db

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response


fun <T> toResultFlow(call: suspend () -> Response<T>): Flow<ApiState<T>> = flow {

    emit(ApiState.Loading)

    try {
        val response = call()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(ApiState.Success(it))
            }
        } else {
            response.errorBody()?.let {error->
                error.close()
                emit(ApiState.Failure(error.string()))
            }
        }

    } catch (e: Exception) {
        Log.e("Exception",e.localizedMessage.toString())
        // emit(ApiState.Failure(e.message?:"Failed Api response"))
    }

}.flowOn(Dispatchers.IO)