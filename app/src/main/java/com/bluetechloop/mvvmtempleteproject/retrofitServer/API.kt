package com.example.zameedar.retrofitServer

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class API @Inject constructor(private val retrofit: Retrofit) {

    val service: ApiInterface =
        retrofit.create(ApiInterface::class.java)
}