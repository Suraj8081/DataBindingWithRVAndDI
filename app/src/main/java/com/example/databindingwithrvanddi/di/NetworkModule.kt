package com.example.databindingwithrvanddi.di

import android.app.Application
import com.example.databindingwithrvanddi.apiInterface.GetAllApis
import com.example.databindingwithrvanddi.utils.Const
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Const.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Singleton
    @Provides
    fun getAPIs(retrofit: Retrofit):GetAllApis{
        return retrofit.create(GetAllApis::class.java)
    }

}