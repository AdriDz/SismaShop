package com.example.sismashop.nav


import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sismashop.sceens.Lista
import com.example.sismashop.sceens.Login
import com.example.sismashop.sceens.Registro
import com.example.sismashop.sceens.Screen

@Composable
fun NavGraph(startDestination: String = Screen.Login.route){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        composable(Screen.Login.route) {
            Login(navController = navController)
        }

        composable(Screen.Lista.route) {
            Lista(navController)
        }
        composable(Screen.Registro.route) {
            Registro(navController = navController)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NavGraphPreview() {
    NavGraph()
}