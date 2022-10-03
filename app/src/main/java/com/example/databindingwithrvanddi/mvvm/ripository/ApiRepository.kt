package com.example.databindingwithrvanddi.mvvm.ripository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databindingwithrvanddi.apiInterface.GetAllApis
import com.example.databindingwithrvanddi.model.APIModel
import com.example.databindingwithrvanddi.model.APIModelMain
import com.example.databindingwithrvanddi.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(private val getAllApis: GetAllApis) {

    private val _apiData = MutableLiveData<NetworkResult<APIModelMain>>()
    val apiData: LiveData<NetworkResult<APIModelMain>>
        get() = _apiData


    suspend fun getAPI() {
        _apiData.postValue(NetworkResult.Loading())
        val response=getAllApis.getAPI()
        handleResponse(response);
    }

    private fun handleResponse(response: Response<APIModelMain>) {
        if (response.isSuccessful && response.body()!=null){
            _apiData.postValue(NetworkResult.Success(response.body()!!))
        }else if(response.errorBody()!=null){
            val errormsg= JSONObject(response.errorBody()!!.charStream().readText())
            _apiData.postValue(NetworkResult.Error(errormsg.getString("messages")))
        }else{
            _apiData.postValue(NetworkResult.Error("Something went wrong"))
        }

    }

}