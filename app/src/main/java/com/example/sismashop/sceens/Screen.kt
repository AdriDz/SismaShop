package com.example.sismashop.sceens


sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Lista : Screen("lista")
    data object Registro : Screen("registro")
}