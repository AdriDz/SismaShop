package com.example.sismashop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sismashop.nav.NavGraph
import com.example.sismashop.ui.theme.SismaShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SismaShopTheme {
                NavGraph()
            }
        }
    }
}
