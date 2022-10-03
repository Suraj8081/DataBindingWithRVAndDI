package com.example.databindingwithrvanddi.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.databindingwithrvanddi.model.APIModelMain
import com.example.databindingwithrvanddi.mvvm.ripository.ApiRepository
import com.example.databindingwithrvanddi.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class APIViewModel @Inject constructor(val apiRepository: ApiRepository):ViewModel() {
    val getApiData:LiveData<NetworkResult<APIModelMain>>
    get()=apiRepository.apiData

    fun getAPiData(){
        viewModelScope.launch {
            apiRepository.getAPI()
        }
    }
}