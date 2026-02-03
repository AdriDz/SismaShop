package com.example.sismashop.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.sismashop.domains.models.Screen
import kotlinx.coroutines.flow.MutableStateFlow

class RegistroScreenViewModel : ViewModel() {

    val name = MutableStateFlow("")
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val repeatPassword = MutableStateFlow("")

    val passwordVisible = MutableStateFlow(false)
    val repeatPasswordVisible = MutableStateFlow(false)

    fun setName(value: String) {
        name.value = value
    }

    fun setEmail(value: String) {
        email.value = value
    }

    fun setPassword(value: String) {
        password.value = value
    }

    fun setRepeatPassword(value: String) {
        repeatPassword.value = value
    }

    fun setPasswordVisible(value: Boolean) {
        passwordVisible.value = value
    }

    fun setRepeatPasswordVisible(value: Boolean) {
        repeatPasswordVisible.value = value
    }

    fun clear() {
        name.value = ""
        email.value = ""
        password.value = ""
        repeatPassword.value = ""
    }
}
