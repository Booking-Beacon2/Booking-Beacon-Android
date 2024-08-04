package com.example.booking_beacon.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_beacon.model.RegisterNormalUserData
import com.example.booking_beacon.repo.UserRepo
import kotlinx.coroutines.launch

class RegisterNormalUserViewModel : ViewModel() {

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> get() = _userId

    fun register(registerNormalUserData: RegisterNormalUserData) {

        viewModelScope.launch {
            kotlin.runCatching {
                UserRepo().registerNormalUser(registerNormalUserData)
            }.onSuccess {
                _userId.value = it
                Log.d("TAG","REGISTER NORMAL SUCCESS");
            }.onFailure {
                Log.d("TAG","REGISTER NORMAL ERROR");
            }
        }
    }
}