package com.example.databindingwithrvanddi.apiInterface

import com.example.databindingwithrvanddi.model.APIModelMain
import retrofit2.Response
import retrofit2.http.GET

interface GetAllApis {

    @GET("/entries")
    suspend fun getAPI(): Response<APIModelMain>

}