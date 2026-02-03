package com.example.sismashop.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sismashop.presentation.ui.components.MenuDeAcciones
import com.example.sismashop.presentation.viewmodel.CatalogViewModel

@Composable
fun NuevaPrendaScreen(
    navController: NavController,
    catalogViewModel: CatalogViewModel = viewModel()
) {
    val rojo = Color(0xFFC8102E)
    val negro = Color.Black

    var nombre by remember { mutableStateOf("") }
    var talla by remember { mutableStateOf("") }
    var precioText by remember { mutableStateOf("") }

    val formValid = nombre.isNotBlank() && talla.isNotBlank() && precioText.toDoubleOrNull() != null

    Scaffold(

        topBar = {
            MenuDeAcciones(navController, "Añadir Prenda")
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(text = "Nombre", color = negro, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Introduce el nombre", color = negro.copy(alpha = 0.7f)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = rojo,
                    focusedBorderColor = rojo,
                    cursorColor = rojo,
                    unfocusedLabelColor = negro,
                    focusedLabelColor = negro
                )
            )

            Text(text = "Talla", color = negro, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = talla,
                onValueChange = { talla = it },
                label = { Text("Introduce la talla", color = negro.copy(alpha = 0.7f)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = rojo,
                    focusedBorderColor = rojo,
                    cursorColor = rojo,
                    unfocusedLabelColor = negro,
                    focusedLabelColor = negro
                )
            )

            Text(text = "Precio", color = negro, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = precioText,
                onValueChange = { precioText = it },
                label = { Text("Introduce el precio", color = negro.copy(alpha = 0.7f)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = rojo,
                    focusedBorderColor = rojo,
                    cursorColor = rojo,
                    unfocusedLabelColor = negro,
                    focusedLabelColor = negro
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val precio = precioText.toDoubleOrNull() ?: 0.0
                    catalogViewModel.addCloth(
                        nombre = nombre,
                        precio = precio,
                        talla = talla
                    )
                    navController.popBackStack() // volver al catálogo
                },
                enabled = formValid,
                colors = ButtonDefaults.buttonColors(containerColor = negro),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Guardar",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NuevaPrendaPreview() {
    NuevaPrendaScreen(navController = rememberNavController())
}

