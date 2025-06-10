package com.example.practica12



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cryptofinder.presentation.ui.CryptoScreen
import com.example.practica12.ui.theme.Practica12Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica12Theme {
            CryptoScreen()
            }
        }
    }
}
