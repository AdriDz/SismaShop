package com.example.sismashop.presentation.ui.screen

import android.transition.Scene
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sismashop.domains.models.Screen
import com.example.sismashop.presentation.viewmodel.RegistroScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Registro(navController: NavController,
    registroScreenViewModel: RegistroScreenViewModel = viewModel(),

) {
    val name by registroScreenViewModel.name.collectAsState()
    val email by registroScreenViewModel.email.collectAsState()
    val password by registroScreenViewModel.password.collectAsState()
    val repeatPassword by registroScreenViewModel.repeatPassword.collectAsState()
    val passwordVisible by registroScreenViewModel.passwordVisible.collectAsState()
    val repeatPasswordVisible by registroScreenViewModel.repeatPasswordVisible.collectAsState()

    val rojo = Color(0xFFC8102E)
    val negro = Color.Black

    val passwordsMatch = password == repeatPassword && password.isNotBlank()
    val formValid =
        name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && repeatPassword.isNotBlank() && passwordsMatch

    val scrollState = rememberScrollState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(12.dp),
        snackbarHost = { SnackbarHost(snackbarHostState) },

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Icon(
                Icons.Filled.ChevronLeft,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(start = 4.dp)
                    .align(Alignment.Start)
                    .size(28.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Registro",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = negro,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Crea una cuenta en la tienda de ", color = negro, fontSize = 14.sp)
                Text(
                    text = "Sisma",
                    fontWeight = FontWeight.Bold,
                    color = negro,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                // Nombre
                Text(
                    "Nombre",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = negro,
                    modifier = Modifier.align(Alignment.Start)
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { registroScreenViewModel.setName(it) },
                    label = { Text("Introduce tu nombre", color = negro.copy(alpha = 0.7f), fontSize = 14.sp) },
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

                // Email
                Text(
                    "Email",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = negro,
                    modifier = Modifier.align(Alignment.Start)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { registroScreenViewModel.setEmail(it) },
                    label = { Text("Introduce tu email", color = negro.copy(alpha = 0.7f), fontSize = 14.sp) },
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

                // Contraseña
                Text(
                    "Contraseña",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = negro,
                    modifier = Modifier.align(Alignment.Start)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { registroScreenViewModel.setPassword(it) },
                    label = { Text("Introduce tu contraseña", color = negro.copy(alpha = 0.7f), fontSize = 14.sp) },
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon =
                            if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        IconButton(onClick = {
                            registroScreenViewModel.setPasswordVisible(!passwordVisible)
                        }) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = rojo,
                        focusedBorderColor = rojo,
                        cursorColor = rojo,
                        unfocusedLabelColor = negro,
                        focusedLabelColor = negro
                    )
                )

                // Repetir contraseña
                Text(
                    "Repetir contraseña",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = negro,
                    modifier = Modifier.align(Alignment.Start)
                )
                OutlinedTextField(
                    value = repeatPassword,
                    onValueChange = { registroScreenViewModel.setRepeatPassword(it) },
                    label = { Text("Repite tu contraseña", color = negro.copy(alpha = 0.7f), fontSize = 14.sp) },
                    singleLine = true,
                    isError = repeatPassword.isNotBlank() && !passwordsMatch,
                    visualTransformation = if (repeatPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon =
                            if (repeatPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        IconButton(onClick = {
                            registroScreenViewModel.setRepeatPasswordVisible(!repeatPasswordVisible)
                        }) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = if (passwordsMatch || repeatPassword.isBlank()) rojo else Color.Red,
                        focusedBorderColor = if (passwordsMatch || repeatPassword.isBlank()) rojo else Color.Red,
                        cursorColor = rojo,
                        unfocusedLabelColor = negro,
                        focusedLabelColor = negro
                    )
                )

                // Botón crear cuenta: solo habilitado si el formulario es válido
                Button(
                    onClick = {navController.navigate(Screen.Catalogo.route)
                        scope.launch {
                            snackbarHostState.showSnackbar("Creando Cuenta")
                            delay(3000L)
                            navController.navigate(Screen.Catalogo.route)
                        }},
                    enabled = formValid,
                    colors = ButtonDefaults.buttonColors(containerColor = negro),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        "Crear Cuenta",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }

                // Limpiar
                Button(
                    onClick = { registroScreenViewModel.clear() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .height(44.dp)
                        .fillMaxWidth(0.5f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Limpiar", fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Frase registro – siempre visible gracias al scroll
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 4.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "¿Ya tienes cuenta? ", color = negro, fontSize = 14.sp)
                TextButton(onClick = { navController.navigate(Screen.Login.route)
                    scope.launch {

                        snackbarHostState.showSnackbar("Yendo a Inicio De Sesion")
                        delay(3000L)
                        navController.navigate(Screen.Login.route)
                    }}) {
                    Text(
                        text = "Inicio Sesion",
                        fontWeight = FontWeight.Bold,
                        color = negro,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RegistroPreview() {
    Registro(navController = rememberNavController())
}
