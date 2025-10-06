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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishapp.ui.theme.EnglishAppTheme
import androidx.compose.material3.MaterialTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishAppTheme {
                ProfileScreen(
                    onBack = { finish() },
                    onSwitchDark = {
                        val currentTheme = ThemeManager.isDarkTheme(this)
                        ThemeManager.setDarkTheme(this, !currentTheme)
                        recreate()
                    },
                    onChangeLanguage = {
                        val intent = Intent(this, LanguageSelectActivity::class.java)
                        startActivity(intent)
                    },
                    onChangeImage = {
                        val intent = Intent(this, ProfileImageActivity::class.java)
                        startActivity(intent)
                    },
                    onLogout = {
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
fun ProfileScreen(
    onBack: () -> Unit,
    onSwitchDark: () -> Unit,
    onChangeLanguage: () -> Unit,
    onChangeImage: () -> Unit,
    onLogout: () -> Unit
) {

    val context = androidx.compose.ui.platform.LocalContext.current
    val isDarkTheme = ThemeManager.isDarkTheme(context)

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(231.dp)
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
                text = "Your profile,Name",
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                lineHeight = 28.sp,
                letterSpacing = 0.01.sp,
                modifier = Modifier
                    .offset(x = 24.dp, y = 183.dp)
                    .width(173.dp)
                    .height(28.dp)
            )
        }

        Box(
            modifier = Modifier
                .offset(x = 24.dp, y = 44.dp)
                .size(134.dp)
                .background(Color(0xFF5BA890), CircleShape)
                .clickable { onChangeImage() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "👨‍💻",
                fontSize = 80.sp,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 528.dp)
                .padding(horizontal = 24.dp)
        ) {
            ProfileOption(
                text = if (isDarkTheme) "Switch to Light" else "Switch to Dark",
                onClick = onSwitchDark,
                backgroundColor = Color(0xFF5B7BFE),
                textColor = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileOption(
                text = "Change mother language",
                onClick = onChangeLanguage,
                backgroundColor = Color(0xFF5B7BFE),
                textColor = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileOption(
                text = "Change your image",
                onClick = onChangeImage,
                backgroundColor = Color(0xFF5B7BFE),
                textColor = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE5E5E5)
                )
            ) {
                Text(
                    text = "Logout",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
@Composable
fun ProfileOption(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = Color(0xFF5B7BFE),
    textColor: Color = Color.White
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
            letterSpacing = 0.01.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}