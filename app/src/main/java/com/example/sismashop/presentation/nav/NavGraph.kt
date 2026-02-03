package com.example.sismashop.presentation.nav


import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sismashop.presentation.ui.screens.Lista

import com.example.sismashop.domains.models.Screen
import com.example.sismashop.presentation.ui.screen.Login
import com.example.sismashop.presentation.ui.screen.Registro
import com.example.sismashop.presentation.ui.screens.NuevaPrendaScreen

@Composable
fun NavGraph(startDestination: String = Screen.Login.route){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        composable(Screen.Login.route) {
            Login(navController)
        }

        composable(Screen.Catalogo.route) {
            Lista(navController)
        }
        composable(Screen.Registro.route) {
            Registro(navController)
        }
        composable(Screen.NuevaPrenda.route) {
            NuevaPrendaScreen(navController)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NavGraphPreview() {
    NavGraph()
}