package com.example.englishapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishapp.ui.theme.EnglishAppTheme

class GuessAnimalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishAppTheme {
                GuessAnimalScreen(
                    onBack = { finish() }
                )
            }
        }
    }
}

@Composable
fun GuessAnimalScreen(onBack: () -> Unit) {
    var currentScreen by remember { mutableStateOf<GuessAnimalScreenState>(GuessAnimalScreenState.Exercise) }
    var currentQuestion by remember { mutableStateOf(3) }
    var totalQuestions by remember { mutableStateOf(41) }
    var userAnswer by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        when (currentScreen) {
            is GuessAnimalScreenState.Exercise -> {
                ExerciseScreen(
                    currentQuestion = currentQuestion,
                    totalQuestions = totalQuestions,
                    userAnswer = userAnswer,
                    onAnswerChange = { userAnswer = it },
                    onCheckAnswer = {
                        val normalizedAnswer = userAnswer.trim().lowercase()
                        if (normalizedAnswer == "raccoon" ||
                            normalizedAnswer == "racoon") {
                            currentScreen = GuessAnimalScreenState.Success
                        } else {
                            currentScreen = GuessAnimalScreenState.Error(
                                correctAnswer = "Racoon"
                            )
                        }
                    },
                    onBack = onBack
                )
            }
            is GuessAnimalScreenState.Success -> {
                SuccessScreen(
                    onNext = {
                        currentQuestion++
                        userAnswer = ""
                        currentScreen = GuessAnimalScreenState.Exercise
                    },
                    onBack = onBack
                )
            }
            is GuessAnimalScreenState.Error -> {
                val errorState = currentScreen as GuessAnimalScreenState.Error
                ErrorScreen(
                    correctAnswer = errorState.correctAnswer,
                    onNext = {
                        currentQuestion++
                        userAnswer = ""
                        currentScreen = GuessAnimalScreenState.Exercise
                    },
                    onTryAgain = {
                        userAnswer = ""
                        currentScreen = GuessAnimalScreenState.Exercise
                    },
                    onBack = onBack
                )
            }
        }
    }
}

sealed class GuessAnimalScreenState {
    object Exercise : GuessAnimalScreenState()
    object Success : GuessAnimalScreenState()
    data class Error(val correctAnswer: String) : GuessAnimalScreenState()
}

@Composable
fun ExerciseScreen(
    currentQuestion: Int,
    totalQuestions: Int,
    userAnswer: String,
    onAnswerChange: (String) -> Unit,
    onCheckAnswer: () -> Unit,
    onBack: () -> Unit
) {
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
                text = "Guess the animal",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Center),
                lineHeight = 28.sp,
                letterSpacing = 0.01.sp,
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier
                .offset(x = 24.dp, y = 124.dp)
                .width(328.dp)
                .height(328.dp)
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.animal),
                contentDescription = "Animal to guess",
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Fit
            )
        }

        Text(
            text = "Write who is on image",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp,
            letterSpacing = 0.01.sp,
            modifier = Modifier
                .offset(x = 24.dp, y = 468.dp)
                .width(327.dp),
            textAlign = TextAlign.Start
        )

        OutlinedTextField(
            value = userAnswer,
            onValueChange = onAnswerChange,
            modifier = Modifier
                .offset(x = 24.dp, y = 496.dp)
                .width(327.dp)
                .height(56.dp),
            placeholder = {
                Text(
                    text = "Enter animal name...",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            },
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Button(
            onClick = onCheckAnswer,
            modifier = Modifier
                .offset(x = 24.dp, y = 656.dp)
                .width(327.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5B7BFE)
            ),
            shape = RoundedCornerShape(12.dp),
            enabled = userAnswer.isNotEmpty()
        ) {
            Text(
                text = "Check",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun SuccessScreen(onNext: () -> Unit, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp)
                .background(Color(0xFF5BA890))
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
                text = "Guess the animal",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Center),
                lineHeight = 28.sp,
                letterSpacing = 0.01.sp,
                textAlign = TextAlign.Center
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "🎉",
                fontSize = 160.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .offset(y = (-50).dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Holy Molly! That is Right!",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )
        }

        Button(
            onClick = onNext,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-100).dp)
                .width(327.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5B7BFE)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Next",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


@Composable
fun ErrorScreen(
    correctAnswer: String,
    onNext: () -> Unit,
    onTryAgain: () -> Unit,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp)
                .background(Color(0xFFD6185D))
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
                text = "Guess the animal",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Center),
                lineHeight = 28.sp,
                letterSpacing = 0.01.sp,
                textAlign = TextAlign.Center
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "😿",
                fontSize = 160.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .offset(y = (-50).dp)
            )

            Spacer(modifier = Modifier.height(32.dp))


            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Eh? Wrong answer :(",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "That is: $correctAnswer",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-50).dp)
                .width(327.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = onNext,
                modifier = Modifier
                    .width(327.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B7BFE)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onTryAgain,
                modifier = Modifier
                    .width(327.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B7BFE)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Try again",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}