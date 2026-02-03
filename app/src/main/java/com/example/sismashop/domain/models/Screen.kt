package com.example.sismashop.domains.models

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Catalogo : Screen("catalogo")
    data object Registro : Screen("registro")
    object NuevaPrenda : Screen("nueva_prenda")
}