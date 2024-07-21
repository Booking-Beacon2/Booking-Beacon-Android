package com.example.booking_beacon.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booking_beacon.enums.LoginType
import com.example.booking_beacon.utils.NavRoute
import com.example.booking_beacon.utils.RouteAction


@Composable
fun SelectLoginTypeScreen(routeAction: RouteAction) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = { routeAction.navTo("${NavRoute.LoginScreen}/${LoginType.Partner}") }, modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f), shape = RectangleShape
            ) {
                Text(text = "파트너", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {routeAction.navTo("${NavRoute.LoginScreen}/${LoginType.Normal}")}, modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .background(Color.Cyan), shape = RectangleShape
            ) {
                Text(text = "일반", fontSize = 20.sp)
            }
        }
    }
}