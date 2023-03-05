package com.example.fooddelivery.presentation.homeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.core.ApiState
import com.example.fooddelivery.data.homeScreen.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repositoryImpl: RepositoryImpl) : ViewModel() {

    private var _responseInfo = MutableLiveData<ApiState>(ApiState.ApiEmpty)
    var responseInfo: LiveData<ApiState> = _responseInfo

    fun response() {
        _responseInfo.value = ApiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val getTest = repositoryImpl.fetchUser()
            if (getTest.isSuccessful) {
                delay(1500)
                _responseInfo.postValue(ApiState.Success(getTest.body()?.test!!))
            }
        }
    }
}