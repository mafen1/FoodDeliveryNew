package com.example.fooddelivery.presentation.loginScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.data.login.models.Login
import com.example.fooddelivery.data.login.repository.LoginRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
//todo rename variables and function
class LoginViewModel @Inject constructor(val loginRepositoryImpl: LoginRepositoryImpl) :
    ViewModel() {
    private var _login: MutableLiveData<Login> = MutableLiveData()
    var login: LiveData<Login> = _login

    fun loginPeople(login: Login) {
        viewModelScope.launch(Dispatchers.IO) {
            loginRepositoryImpl.createAccount(login)
        }
    }
}