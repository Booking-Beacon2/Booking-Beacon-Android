package com.example.booking_beacon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.booking_beacon.ui.theme.BookingBeaconTheme
import com.example.booking_beacon.utils.NavigationGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookingBeaconTheme {
                NavigationGraph()
            }
        }
    }
}
