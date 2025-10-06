package com.example.englishapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishapp.ui.theme.EnglishAppTheme

class WordPracticeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishAppTheme {
                WordPracticeScreen(
                    onBack = { finish() }
                )
            }
        }
    }
}

@Composable
fun WordPracticeScreen(onBack: () -> Unit) {
    var currentQuestion by remember { mutableStateOf(1) }
    var totalQuestions by remember { mutableStateOf(4) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }

    val currentWord = "gardener"
    val currentPronunciation = "[ 'gardna ]"
    val answers = listOf("Муха", "Садовник", "Гладиолус", "Собака")
    val correctAnswer = "Садовник"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp)
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
                text = "Word practice",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Center),
                lineHeight = 28.sp,
                letterSpacing = 0.01.sp,
                textAlign = TextAlign.Center
            )
        }

        Text(
            text = currentWord,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .offset(x = 24.dp, y = 140.dp)
                .width(327.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = currentPronunciation,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .offset(x = 24.dp, y = 190.dp)
                .width(327.dp),
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .offset(x = 24.dp, y = 260.dp)
                .width(327.dp)
        ) {
            answers.forEach { answer ->
                AnswerOption(
                    text = answer,
                    isSelected = selectedAnswer == answer,
                    isCorrect = isCorrect == true && answer == correctAnswer,
                    isWrong = isCorrect == false && answer == selectedAnswer && answer != correctAnswer,
                    onClick = {
                        if (isCorrect == null) {
                            selectedAnswer = answer
                            isCorrect = (answer == correctAnswer)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        if (isCorrect != null) {
            Button(
                onClick = {
                    if (currentQuestion < totalQuestions) {
                        currentQuestion++
                        selectedAnswer = null
                        isCorrect = null
                    } else {
                        onBack()
                    }
                },
                modifier = Modifier
                    .offset(x = 24.dp, y = 656.dp)
                    .width(327.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B7BFE)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (currentQuestion < totalQuestions) "Next" else "Finish",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun AnswerOption(
    text: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    isWrong: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        isCorrect -> Color(0xFF5BA890)
        isWrong -> Color(0xFFF76400)
        isSelected -> Color(0xFF5B7BFE)
        else -> MaterialTheme.colorScheme.surface.copy(alpha = 0.1f)
    }

    val textColor = when {
        isCorrect || isWrong || isSelected -> Color.White
        else -> MaterialTheme.colorScheme.onBackground
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 24.sp
        )
    }
}