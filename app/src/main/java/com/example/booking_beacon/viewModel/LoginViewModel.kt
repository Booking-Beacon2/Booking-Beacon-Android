package com.example.booking_beacon.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_beacon.model.Token
import com.example.booking_beacon.model.user.LoginData
import com.example.booking_beacon.repo.UserRepo
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val _token = MutableLiveData<Token>()
    val token: LiveData<Token> get() = _token

    fun login(loginData: LoginData) {
        viewModelScope.launch{
            kotlin.runCatching {
                UserRepo().login(loginData)
            }.onSuccess {
                _token.value = it
            }.onFailure {
                Log.d("ERROR","LOGIN ERROR");
            }

        }
    }


}