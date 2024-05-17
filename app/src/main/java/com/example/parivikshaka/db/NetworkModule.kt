package com.example.parivikshaka.db

import android.provider.SyncStateContract
import com.example.parivikshaka.api.Constant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Provides
//    @Singleton
//    fun roomdatabase(apiService: ApiService): CommongRepository {
//        return CommongRepository(apiService)
//    }





    @Provides
    @Singleton
    fun providesMoshi(): Moshi =
        Moshi
            .Builder()
            .run {
                add(KotlinJsonAdapterFactory())
                build()
            }

    @Provides
    @Singleton
    @TokenApiService
    fun providesApiServicewithToken(moshi: Moshi, client: OkHttpClient): ApiServices =
        Retrofit
            .Builder()
            .run {
                baseUrl(Constant.AppUrl)
                addConverterFactory(GsonConverterFactory.create())
                client(client)
                build()
            }.create(ApiServices::class.java)


    @Provides
    @Singleton
    @CommonApiService
    fun providesApiServiceWithoutToken(moshi: Moshi, client: OkHttpClient): ApiServices =
        Retrofit
            .Builder()
            .run {
                baseUrl(Constant.AppUrl)
                addConverterFactory(GsonConverterFactory.create())
                client(client)
                build()
            }.create(ApiServices::class.java)



    @Provides
    @Singleton
    fun providesOkHttp(oAuthInterceptor: OAuthInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(oAuthInterceptor)
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .build()
    }



}