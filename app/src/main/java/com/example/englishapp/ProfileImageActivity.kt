package com.example.englishapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import com.example.englishapp.ui.theme.EnglishAppTheme

class ProfileImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishAppTheme {
                ProfileImageScreen(
                    onBack = { finish() },
                    onUseImage = {
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun ProfileImageScreen(
    onBack: () -> Unit,
    onUseImage: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF1B1815))) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(104.dp)
                .background(Color(0xFF410FA3))
                .padding(bottom = 20.dp)
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
                text = "Your photo is gorgeous!",
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                lineHeight = 28.sp,
                letterSpacing = 0.01.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = 10.dp)
                    .width(236.dp)
                    .height(28.dp),
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 104.dp)
        ) {
            Text(
                text = "Just resize that photo\nfor fit in square",
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                lineHeight = 28.sp,
                letterSpacing = 0.01.sp,
                modifier = Modifier
                    .offset(x = 24.dp, y = 30.dp)
                    .width(222.dp)
                    .height(56.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(253.dp)
                    .offset(y = 100.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.Gray, RoundedCornerShape(100.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image29),
                        contentDescription = "Profile photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .background(Color.Gray, RoundedCornerShape(100.dp))
                    )
                }
            }

            Button(
                onClick = onUseImage,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .offset(y = 400.dp)
                    .padding(horizontal = 0.dp),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF410FA3)
                )
            ) {
                Text(
                    text = "Use this image",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}