package com.example.englishapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishapp.ui.theme.EnglishAppTheme

class ForgotPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishAppTheme {
                ForgotPasswordScreen(
                    onBack = { finish() },
                    onResetPassword = { email ->
                        val intent = Intent(this, LanguageSelectActivity::class.java)
                        startActivity(intent)
                        finish()
                    },
                    onLogin = {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun ForgotPasswordScreen(
    onBack: () -> Unit,
    onResetPassword: (String) -> Unit,
    onLogin: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val confirmPasswordVisible = remember { mutableStateOf(false) }
    val acceptedRules = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(102.dp)
                .background(Color(0xFF410FA3))
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.offset(x = 16.dp, y = 20.dp)
            ) {
                Text(
                    text = "<",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "Signup",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center),
                lineHeight = 28.sp,
                letterSpacing = 0.01.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 102.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Choose a Password",
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 28.sp,
                    modifier = Modifier.width(263.dp)
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 80.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Password",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                BasicTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    decorationBox = { innerTextField ->
                        Box {
                            if (password.value.isEmpty()) {
                                Text(
                                    text = "••••••••",
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                    fontSize = 16.sp
                                )
                            }
                            innerTextField()

                            Text(
                                text = if (passwordVisible.value) "HIDE" else "SHOW",
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .clickable { passwordVisible.value = !passwordVisible.value }
                            )
                        }
                    }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 120.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Confirm Password",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                BasicTextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    visualTransformation = if (confirmPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    decorationBox = { innerTextField ->
                        Box {
                            if (confirmPassword.value.isEmpty()) {
                                Text(
                                    text = "••••••••",
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                    fontSize = 16.sp
                                )
                            }
                            innerTextField()

                            Text(
                                text = if (confirmPasswordVisible.value) "HIDE" else "SHOW",
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .clickable { confirmPasswordVisible.value = !confirmPasswordVisible.value }
                            )
                        }
                    }
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 160.dp)
                    .padding(horizontal = 24.dp)
                    .clickable { acceptedRules.value = !acceptedRules.value },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .border(
                            width = 2.dp,
                            color = Color(0xFF410FA3),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .background(
                            color = if (acceptedRules.value) Color(0xFF410FA3) else Color.Transparent,
                            shape = RoundedCornerShape(4.dp)
                        )
                ) {
                    if (acceptedRules.value) {
                        Text(
                            text = "✓",
                            color = Color.White,
                            fontSize = 12.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color(0xFF410FA3))) {
                            append("I have made myself acquainted with the Rules, ")
                        }
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                            append("and accept all its provisions")
                        }
                    },
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
            }

            Button(
                onClick = {
                    onResetPassword(email.value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 200.dp)
                    .padding(horizontal = 24.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF410FA3)
                )
            ) {
                Text(
                    text = "Signup",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            TextButton(
                onClick = onLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 220.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Already you member? ",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "Login",
                        color = Color(0xFF410FA3),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}