package com.bluetechloop.mvvmtempleteproject.utlis

import android.content.Context
import com.bluetechloop.mvvmtempleteproject.MainApplication
import com.example.zameedar.retrofitServer.ApiInterface
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesBaseUrl() : String = "http://arti.redbrickssolution.com/api/"

    @Singleton
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
        .writeTimeout(5, TimeUnit.MINUTES) // write timeout
        .readTimeout(5, TimeUnit.MINUTES) // read timeout
        .build();

    @Provides
    @Singleton
    fun providesRetrofit():Retrofit
    {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        client.connectionPool.evictAll()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return MainApplication.instance
    }
    @Provides
    @Singleton
    fun providesAPI(retrofit:Retrofit):ApiInterface=
        retrofit.create(ApiInterface::class.java)
}