package com.example.sismashop.presentation.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sismashop.domains.models.Cloths
import com.example.sismashop.domains.models.Screen
import com.example.sismashop.presentation.ui.components.MenuDeAcciones
import com.example.sismashop.presentation.viewmodel.CatalogViewModel

@Composable
fun Lista(
    navController: NavHostController,
    catalogViewModel: CatalogViewModel = viewModel()
) {
    val cloths by catalogViewModel.cloths.collectAsState()

    val rojo = Color(0xFFC8102E)
    val negro = Color.Black

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            MenuDeAcciones(navController, "Catalogo")
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.NuevaPrenda.route) },
                containerColor = negro,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir prenda"
                )
            }
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {

            Text(
                text = "Catálogo de ropa",
                color = negro,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Explora las prendas disponibles en Sisma",
                color = negro.copy(alpha = 0.8f),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(cloths, key = { it.id }) { cloth ->
                    ClothCard(
                        cloth = cloth,
                        rojo = rojo,
                        negro = negro,
                        onDelete = { catalogViewModel.removeCloth(cloth.id) },
                        onModify = { catalogViewModel.modifyCloth(cloth.id) }
                    )
                }
            }
        }
    }
}
@Composable
fun ClothCard(
    cloth: Cloths,
    rojo: Color,
    negro: Color,
    onDelete: () -> Unit,
    onModify: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(14.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = cloth.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    color = negro,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = if (expanded) "Contraer" else "Expandir",
                        tint = negro
                    )
                }
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Talla: ${cloth.talla}",
                    color = negro,
                    fontSize = 14.sp
                )
                Text(
                    text = "Precio: ${cloth.precio} €",
                    color = negro,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onModify,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = negro
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Modificar",
                            modifier = Modifier.size(18.dp),
                            tint = negro
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Modificar", fontSize = 14.sp)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { showDeleteDialog = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = rojo,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar",
                            modifier = Modifier.size(18.dp),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Eliminar", fontSize = 14.sp)
                    }
                }
            }
        }
    }

    // Diálogo de confirmación de borrado
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(text = "Eliminar prenda") },
            text = { Text("¿Seguro que quieres borrar la prenda?") },
            confirmButton = {
                Button(
                    onClick = {
                        showDeleteDialog = false
                        onDelete()
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Preview
@Composable
fun ListaPreview() {
    Lista(navController = rememberNavController())
}
