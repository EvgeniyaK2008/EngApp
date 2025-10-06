package com.example.englishapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishapp.ui.theme.EnglishAppTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishAppTheme {
                LoginScreen(
                    onBack = {
                        finish()
                    },
                    onLogin = { enteredEmail, enteredPassword ->
                        // Упрощенная логика - всегда разрешаем вход
                        navigateToHome()
                    },
                    onForgotPassword = {
                        val intent = Intent(this, ForgotPasswordActivity::class.java)
                        startActivity(intent)
                    },
                    onSignup = {
                        val intent = Intent(this, SignupActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showError(message: String) {
        // Можно добавить Toast или Snackbar для показа ошибки
        println("Ошибка входа: $message")
    }
}


@Composable
fun LoginScreen(
    onBack: () -> Unit,
    onLogin: (String, String) -> Unit,
    onForgotPassword: () -> Unit,
    onSignup: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        // Верхний синий блок
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
                text = "Login",
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
            // Иллюстрация и текст
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.illustrations3),
                    contentDescription = "Login illustration",
                    modifier = Modifier
                        .width(105.dp)
                        .height(82.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "For free, join now and start learning",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground, // Адаптивный цвет
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    modifier = Modifier.width(263.dp)
                )
            }

            // Поле Email
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 100.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Email Address",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground, // Адаптивный цвет
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Поле ввода Email
                BasicTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f), // Адаптивный цвет
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    decorationBox = { innerTextField ->
                        if (email.value.isEmpty()) {
                            Text(
                                text = "Email",
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), // Адаптивный цвет
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                )
            }

            // Поле Password
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 160.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Password",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground, // Адаптивный цвет
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Поле ввода Password
                BasicTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f), // Адаптивный цвет
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    decorationBox = { innerTextField ->
                        Box {
                            if (password.value.isEmpty()) {
                                Text(
                                    text = "••••••••",
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), // Адаптивный цвет
                                    fontSize = 16.sp
                                )
                            }
                            innerTextField()

                            // Текст для показа/скрытия пароля
                            Text(
                                text = if (passwordVisible.value) "HIDE" else "SHOW",
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f), // Адаптивный цвет
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .clickable { passwordVisible.value = !passwordVisible.value }
                            )
                        }
                    }
                )
            }

            // Кнопка Forgot Password
            TextButton(
                onClick = onForgotPassword,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 200.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Forgot Password",
                    color = Color(0xFFD6185D), // Оставляем красный для акцента
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
            }

            // Кнопка Login
            Button(
                onClick = { onLogin(email.value, password.value) },
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 240.dp)
                    .padding(horizontal = 24.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF410FA3) // Оставляем фиолетовый
                )
            ) {
                Text(
                    text = "Login",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Кнопка Signup
            TextButton(
                onClick = onSignup,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 290.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Not you member? ",
                        color = MaterialTheme.colorScheme.onBackground, // Адаптивный цвет
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "Signup",
                        color = Color(0xFF5B7BFE), // Оставляем синий для акцента
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}