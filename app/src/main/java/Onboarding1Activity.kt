package com.example.englishapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.englishapp.ui.theme.EnglishAppTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import com.example.englishapp.LoginActivity
class Onboarding1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishAppTheme {
                Onboarding1Screen(
                    onNext = {

                        OnboardingManager.setOnboardingStep(this, 2)
                        val intent = Intent(this, Onboarding2Activity::class.java)
                        startActivity(intent)
                    },
                    onSkip = {
                        OnboardingManager.setOnboardingCompleted(this, true)
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
fun Onboarding1Screen(onNext: () -> Unit, onSkip: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 147.dp)
                .width(240.dp)
                .height(220.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.illustrations),
                contentDescription = "Learn any time, anywhere",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 482.dp)
                .width(40.dp)
                .height(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFF76400)))
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFCCCCCC)))
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFCCCCCC)))
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 510.dp)
                .width(263.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Learn any time, anywhere",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
                lineHeight = 28.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "With conversation-based learning, you'll be talking from lesson one",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )
        }

        Button(
            onClick = onNext,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 650.dp)
                .width(327.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5B7BFE)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Next",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }


        Text(
            text = "Skip onboarding",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 728.dp)
                .width(150.dp)
                .height(20.dp)
                .clickable { onSkip() }
        )
    }
}