package com.example.sismashop.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.sismashop.domains.models.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginScreenViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible: StateFlow<Boolean> = _passwordVisible

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setPasswordVisible(visible: Boolean) {
        _passwordVisible.value = visible
    }


    fun clear() {
        _email.value = ""
        _password.value = ""
        _passwordVisible.value = false
    }
}