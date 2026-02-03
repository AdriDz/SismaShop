package com.example.sismashop.presentation.viewmodel


import androidx.lifecycle.ViewModel
import com.example.sismashop.domains.models.Cloths
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CatalogViewModel : ViewModel() {

    private val _cloths = MutableStateFlow<List<Cloths>>(
        listOf(
            Cloths(1, "Camiseta básica blanca", 9.99, "M"),
            Cloths(2, "Pantalón vaquero azul", 24.99, "L"),
            Cloths(3, "Sudadera con capucha", 29.99, "S"),
            Cloths(4, "Chaqueta negra", 49.99, "M"),
            Cloths(5, "Vestido rojo", 39.99, "S")
        )
    )
    val cloths: StateFlow<List<Cloths>> = _cloths

    fun removeCloth(id: Int) {
        _cloths.value = _cloths.value.filter { it.id != id }
    }

    fun modifyCloth(id: Int) {
        val clothToEdit = _cloths.value.find { it.id == id }
    }


    fun addCloth(nombre: String, precio: Double, talla: String) {
        val currentList = _cloths.value
        val newId = (currentList.maxOfOrNull { it.id } ?: 0) + 1
        val newCloth = Cloths(
            id = newId,
            nombre = nombre,
            precio = precio,
            talla = talla
        )
        _cloths.value = currentList + newCloth
    }

}
