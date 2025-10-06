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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishapp.ui.theme.EnglishAppTheme

class LanguageSelectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishAppTheme {
                LanguageSelectScreen(
                    onBack = { finish() },
                    onLanguageSelected = { selectedLanguage ->
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun LanguageSelectScreen(
    onBack: () -> Unit,
    onLanguageSelected: (String) -> Unit
) {
    val selectedLanguage = remember { mutableStateOf("") }

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
                text = "Language select",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(327.dp),
                lineHeight = 28.sp,
                letterSpacing = 0.01.sp,
                textAlign = TextAlign.Center
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
                    text = "What is your Mother Language?",
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    lineHeight = 28.sp,
                    letterSpacing = 0.01.sp,
                    modifier = Modifier.width(327.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 80.dp)
                    .padding(horizontal = 24.dp)
            ) {
                LanguageOption(
                    language = "Russian",
                    isSelected = selectedLanguage.value == "Russian",
                    onClick = { selectedLanguage.value = "Russian" }
                )

                Spacer(modifier = Modifier.height(12.dp))

                LanguageOption(
                    language = "English",
                    isSelected = selectedLanguage.value == "English",
                    onClick = { selectedLanguage.value = "English" }
                )

                Spacer(modifier = Modifier.height(12.dp))

                LanguageOption(
                    language = "Chinese",
                    isSelected = selectedLanguage.value == "Chinese",
                    onClick = { selectedLanguage.value = "Chinese" }
                )

                Spacer(modifier = Modifier.height(12.dp))

                LanguageOption(
                    language = "Belarus",
                    isSelected = selectedLanguage.value == "Belarus",
                    onClick = { selectedLanguage.value = "Belarus" }
                )

                Spacer(modifier = Modifier.height(12.dp))


                LanguageOption(
                    language = "Kazakh",
                    isSelected = selectedLanguage.value == "Kazakh",
                    onClick = { selectedLanguage.value = "Kazakh" }
                )
            }


            Button(
                onClick = {
                    if (selectedLanguage.value.isNotEmpty()) {
                        onLanguageSelected(selectedLanguage.value)
                    }
                },
                modifier = Modifier
                    .width(327.dp)
                    .height(56.dp)
                    .offset(x = 42.dp, y = 320.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B7BFE)
                ),
                enabled = selectedLanguage.value.isNotEmpty()
            ) {
                Text(
                    text = "Choose",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun LanguageOption(
    language: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = if (isSelected) Color(0xFFF76400) else MaterialTheme.colorScheme.surface.copy(alpha = 0.1f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = language,
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 28.sp,
            letterSpacing = 0.01.sp
        )
    }
}