package com.example.booking_beacon.screen

import android.util.Log
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booking_beacon.enums.UserType
import com.example.booking_beacon.model.user.LoginData
import com.example.booking_beacon.utils.NavRoute
import com.example.booking_beacon.utils.RouteAction
import com.example.booking_beacon.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    routeAction: RouteAction, userType: UserType, loginViewModel: LoginViewModel = viewModel(
    )
) {
    val token by loginViewModel.token.observeAsState()

    val emailInput = remember { mutableStateOf(TextFieldValue()) }

    val passwordInput = remember { mutableStateOf(TextFieldValue()) }

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
                text = "BOOKING\nBEACON\n\n${userType}",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold)
            )

            token?.let {
                Text(text = "LoginSuccess")
                Text(text = "accessToken : ${it.accessToken}")
                Text(text = "refreshToken : ${it.refreshToken}")

            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Column(horizontalAlignment = Alignment.End) {
                    TextWithTextFieldView(
                        title = "ID",
                        hint = "아이디를 입력해주세요", input = emailInput
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextWithTextFieldView(
                        title = "PW",
                        hint = "비밀번호를 입력해주세요",
                        input = passwordInput
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(modifier = Modifier.weight(1f), onClick = {
                        routeAction.navTo("${NavRoute.RegisterScreen}/${userType}")
                    }) {
                        Text(text = "회원가입")
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(modifier = Modifier.weight(1f), onClick = {
                        Log.d("loginInfo", emailInput.value.text)
                        loginViewModel.login(
                            LoginData(
                                emailInput.value.text,
                                passwordInput.value.text,
                                userType
                            )
                        )

                    }) {
                        Text(text = "로그인")
                    }
                }
            }
        }
    }
}

@Composable
fun TextWithTextFieldView(title: String, hint: String, input: MutableState<TextFieldValue>) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = title)
        Spacer(modifier = Modifier.width(16.dp))
        TextField(
            value = input.value,
            onValueChange = { newValue -> input.value = newValue },
            placeholder = { Text(hint) })
    }
}