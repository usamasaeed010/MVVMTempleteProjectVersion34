package com.example.zameedar.repository

import com.example.zameedar.retrofitServer.ApiInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(val api: ApiInterface) {

//    suspend fun getHomeData(): Response<HomeDataModel> {
//        return api.home()
//    }
//
//    suspend fun getHomeVerifiedData(): Response<VerifiedAndNonVerifedModel> {
//        return api.GetHomeArtiDetail()
//    }

}
