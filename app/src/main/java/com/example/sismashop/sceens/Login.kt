package com.example.sismashop.sceens

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sismashop.R
import com.example.sismashop.viewmodel.LoginScreenViewModel

@Composable
fun Login(
    loginScreenViewModel: LoginScreenViewModel = viewModel(),
    navController: NavController
) {
    val email by loginScreenViewModel.email.collectAsState()
    val password by loginScreenViewModel.password.collectAsState()
    val passwordVisible by loginScreenViewModel.passwordVisible.collectAsState()
    val rojo = Color(0xFFC8102E)
    val negro = Color.Black

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Spacer(modifier = Modifier.height(24.dp))

                Icon(
                    Icons.Filled.ChevronLeft,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .align(Alignment.Start)
                        .size(32.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(160.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Inicio de Sesion",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = negro,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Inicia sesion en la tienda de ", color = negro)
                    Text(
                        text = "Sisma",
                        fontWeight = FontWeight.Bold,
                        color = negro
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    Text(
                        "Email",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = negro,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { loginScreenViewModel.setEmail(it) },
                        label = { Text("Introduce tu correo", color = negro.copy(alpha = 0.7f)) },
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

                    Text(
                        "Contraseña",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = negro,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { loginScreenViewModel.setPassword(it) },
                        label = { Text("Introduce tu contraseña", color = negro.copy(alpha = 0.7f)) },
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                            IconButton(onClick = { loginScreenViewModel.setPasswordVisible(!passwordVisible) }) {
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

                    // Botón de iniciar sesión: solo habilitado si hay email y password
                    Button(
                        onClick = { loginScreenViewModel.login(navController) },
                        enabled = email.isNotBlank() && password.isNotBlank(),
                        colors = ButtonDefaults.buttonColors(containerColor = negro),
                        shape = RoundedCornerShape(14),
                        modifier = Modifier
                            .height(52.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            "Iniciar Sesion",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { loginScreenViewModel.clear() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        shape = RoundedCornerShape(10),
                        modifier = Modifier
                            .height(48.dp)
                            .fillMaxWidth(0.6f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Limpiar")
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "¿No tienes cuenta? ", color = negro)
                        TextButton(onClick = { navController.navigate(Screen.Registro.route) }) {
                            Text(
                                text = "Regístrate",
                                fontWeight = FontWeight.Bold,
                                color = negro
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    Login(navController = navController)
}
