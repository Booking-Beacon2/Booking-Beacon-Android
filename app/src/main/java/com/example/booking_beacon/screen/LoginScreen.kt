package com.example.booking_beacon.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booking_beacon.enums.LoginType
import com.example.booking_beacon.utils.NavRoute
import com.example.booking_beacon.utils.RouteAction

@Composable
fun LoginScreen(routeAction: RouteAction, loginType: LoginType) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp, bottom = 100.dp)

        ) {
            Text(
                text = "BOOKING\nBEACON\n\n${loginType}",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold)
            )
            LoginView(loginType, routeAction)
        }
    }
}

@Composable
fun LoginView(loginType: LoginType, routeAction: RouteAction) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Column(horizontalAlignment = Alignment.End) {
            TextWithTextFieldView(
                title = "ID",
                hint = "아이디를 입력해주세요"
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextWithTextFieldView(title = "PW", hint = "비밀번호를 입력해주세요")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(modifier = Modifier.weight(1f), onClick = {
                if (loginType == LoginType.Normal) {
                    routeAction.navTo(NavRoute.RegisterNormalUserScreen.routeName);
                }
            }) {
                Text(text = "회원가입")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(modifier = Modifier.weight(1f), onClick = {}) {
                Text(text = "로그인")
            }
        }
    }
}

@Composable
fun TextWithTextFieldView(title: String, hint: String) {
    var text by remember { mutableStateOf("") }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = title)
        Spacer(modifier = Modifier.width(16.dp))
        TextField(value = text, onValueChange = { text = it }, placeholder = { Text(hint) })
    }
}