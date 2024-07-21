package com.example.booking_beacon.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.booking_beacon.R
import com.example.booking_beacon.utils.RouteAction


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterNormalUserScreen(routeAction: RouteAction) {
    var id by remember { mutableStateOf(TextFieldValue()) }

    var emailInput by remember { mutableStateOf(TextFieldValue()) }

    val shouldShowPassword = remember { mutableStateOf(false) }

    var passwordInput by remember { mutableStateOf(TextFieldValue()) }

    val passwordResource: (Boolean) -> Int = {
        if (it) {
            R.drawable.ic_baseline_visibility_24
        } else {
            R.drawable.ic_baseline_visibility_off_24
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("회원가입")
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(innerPadding)
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = id,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = { newValue -> id = newValue },
                    label = { Text("ID") },
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = emailInput,
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { Log.d("TAG", "TextFieldTest: 체크버튼 클릭") }) {
                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    onValueChange = { newValue -> emailInput = newValue },
                    label = { Text("이메일 주소") },
                    placeholder = { Text("이메일 주소를 입력해 주세요") }
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = passwordInput,
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            Log.d("TAG", "TextFieldTest: 비밀번호 visible 버튼 클릭")
                            shouldShowPassword.value = !shouldShowPassword.value
                        }) {
                            Icon(
                                painter = painterResource(id = passwordResource(shouldShowPassword.value)),
                                contentDescription = null
                            )
                        }
                    },
                    visualTransformation = if (shouldShowPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { newValue -> passwordInput = newValue },
                    label = { Text("비밀번호") },
                    placeholder = { Text("비밀번호를 입력해주세요") }
                )
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(innerPadding)
                    .fillMaxWidth()
            ) {
                Text(text = "회원가입")
            }
        }
    }
}
