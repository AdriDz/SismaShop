package com.example.sismashop.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.sismashop.domains.models.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDeAcciones(
    navController: NavController,
    title: String
) {
    var expanded by remember { mutableStateOf(false) }

    val rojo = Color(0xFFC8102E)
    val negro = Color.Black

    TopAppBar(
        title = {
            Text(
                text = title,
                color = negro
            )
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menú",
                    tint = negro
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                // Añadir prenda
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Añadir prenda",
                            color = negro
                        )
                    },
                    onClick = {
                        expanded = false
                        navController.navigate(Screen.NuevaPrenda.route)
                    }
                )

                HorizontalDivider(color = rojo.copy(alpha = 0.5f))

                // Cerrar sesión
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Cerrar sesión",
                            color = rojo
                        )
                    },
                    onClick = {
                        expanded = false
                        navController.navigate(Screen.Login.route)
                    }
                )
            }
        }
    )
}
