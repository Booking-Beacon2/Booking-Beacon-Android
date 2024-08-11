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
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booking_beacon.R
import com.example.booking_beacon.enums.UserType
import com.example.booking_beacon.model.user.RegisterNormalUserData
import com.example.booking_beacon.model.user.RegisterPartnerUserData
import com.example.booking_beacon.utils.RouteAction
import com.example.booking_beacon.viewModel.RegisterNormalUserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterNormalUserScreen(
    routeAction: RouteAction,
    userType: UserType,
    registerNormalUserViewModel: RegisterNormalUserViewModel = viewModel()
) {

    val userId by registerNormalUserViewModel.userId.observeAsState()

    var idInput by remember { mutableStateOf(TextFieldValue()) }

    var emailInput by remember { mutableStateOf(TextFieldValue()) }

    val shouldShowPassword = remember { mutableStateOf(false) }

    var passwordInput by remember { mutableStateOf(TextFieldValue()) }

    var phoneNumberInput by remember {
        mutableStateOf(TextFieldValue())
    }

    var partnerNameInput by remember {
        mutableStateOf(TextFieldValue())
    }

    var einInput by remember {
        mutableStateOf(TextFieldValue())
    }

    val passwordResource: (Boolean) -> Int = {
        if (it) {
            R.drawable.ic_baseline_visibility_24
        } else {
            R.drawable.ic_baseline_visibility_off_24
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(if (userType == UserType.USER) "일반 회원가입" else "파트너 회원가입") })
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
                    value = idInput,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = { newValue -> idInput = newValue },
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
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = phoneNumberInput,
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { Log.d("TAG", "TextFieldTest: 체크버튼 클릭") }) {
                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    onValueChange = { newValue -> phoneNumberInput = newValue },
                    label = { Text("휴대폰 번호") },
                    placeholder = { Text("휴대폰 번호를 입력해 주세요") }
                )

                if (userType == UserType.PARTNER) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = partnerNameInput,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        onValueChange = { newValue -> partnerNameInput = newValue },
                        label = { Text("회사명") },
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = einInput,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = { newValue ->
                            if (newValue.text.length <= 10) {
                                einInput = newValue
                            }
                        },
                        label = { Text("EIN") },
                    )
                }


            }

            userId?.let { _ ->
                routeAction.goBack()
            } ?: run {
                Text(text = "No User ID registered yet.", color = Color.Red)
            }

            Button(
                onClick = {
                    if (userType == UserType.USER) {

                        val registerNormalUserData = RegisterNormalUserData(
                            idInput.text,
                            passwordInput.text,
                            emailInput.text,
                            phoneNumberInput.text
                        )
                        registerNormalUserViewModel.registerNormalUser(registerNormalUserData)
                    } else {

                        val registerPartnerUserData = RegisterPartnerUserData(
                            idInput.text,
                            passwordInput.text,
                            emailInput.text,
                            phoneNumberInput.text,
                            partnerNameInput.text,
                            einInput.text
                        )
                        registerNormalUserViewModel.registerPartnerUser(registerPartnerUserData)
                    }
                },
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
