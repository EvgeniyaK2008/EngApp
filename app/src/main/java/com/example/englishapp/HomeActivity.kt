package com.example.englishapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishAppTheme {
                HomeScreen(
                    userName = "Emil",
                    onProfileClick = {
                        val intent = Intent(this, ProfileActivity::class.java)
                        startActivity(intent)
                    },
                    onGuessAnimalClick = {
                        val intent = Intent(this, GuessAnimalActivity::class.java)
                        startActivity(intent)
                    },
                    onWordPracticeClick = {
                        val intent = Intent(this, WordPracticeActivity::class.java)
                        startActivity(intent)
                    },
                    onListeningClick = {
                        val intent = Intent(this, ListeningActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    userName: String,
    onProfileClick: () -> Unit,
    onGuessAnimalClick: () -> Unit,
    onWordPracticeClick: () -> Unit,
    onListeningClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(175.dp)
                .background(Color(0xFF410FA3))
                .padding(bottom = 20.dp)
                .clickable { onProfileClick() }
        ) {
            Box(
                modifier = Modifier
                    .offset(x = 24.dp, y = 50.dp)
                    .size(54.dp)
                    .background(Color(0xFFD9D9D9), CircleShape)
                    .clickable { onProfileClick() }
            )

            Text(
                text = "Hello, $userName",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .offset(x = 24.dp, y = 109.dp)
                    .width(106.dp)
                    .height(28.dp),
                lineHeight = 28.sp
            )

            Text(
                text = "Are you ready for learning today?",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .offset(x = 24.dp, y = 142.dp)
                    .width(265.dp)
                    .height(22.dp),
                lineHeight = 22.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 175.dp)
        ) {

            Text(
                text = "Top users",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp,
                letterSpacing = 0.01.sp,
                modifier = Modifier
                    .offset(x = 24.dp, y = 11.dp)
                    .width(88.dp)
                    .height(24.dp)
            )

            TopUserItem(
                modifier = Modifier.offset(y = 30.dp),
                emoji = "👨🏻‍🎨",
                name = "Vincent van Gogh",
                points = "12 points"
            )

            TopUserItem(
                modifier = Modifier.offset(y = 50.dp),
                emoji = "👨🏻‍🔬",
                name = "Dmitri Ivanovich Mendeleev",
                points = "10 points"
            )

            TopUserItem(
                modifier = Modifier.offset(y = 70.dp),
                emoji = "🧛🏻‍♂️",
                name = "Vlad Tepes",
                points = "8 points"
            )

            Text(
                text = "Available exercises",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp,
                letterSpacing = 0.01.sp,
                modifier = Modifier
                    .offset(x = 24.dp, y = 90.dp)
                    .width(185.dp)
                    .height(24.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 110.dp)
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                ExerciseItem(
                    title = "Guess the animal",
                    emoji = "🐻‍❄️",
                    color = Color(0xFF5B7BFE),
                    onClick = onGuessAnimalClick
                )


                ExerciseItem(
                    title = "Word practice",
                    emoji = "✏️",
                    color = Color(0xFFD6185D),
                    onClick = onWordPracticeClick
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 130.dp)
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                ExerciseItem(
                    title = "Listening",
                    emoji = "🔊",
                    color = Color(0xFFF76400),
                    onClick = onListeningClick
                )


                ExerciseItem(
                    title = "Game",
                    emoji = "🎮",
                    color = Color(0xFF5BA890),
                    onClick = {

                    }
                )
            }
        }
    }
}

@Composable
fun TopUserItem(
    modifier: Modifier = Modifier,
    emoji: String,
    name: String,
    points: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(64.dp)
            .background(Color(0xFFE5E5E5), RoundedCornerShape(20.dp))
    ) {
        // Emoji
        Text(
            text = emoji,
            fontSize = 24.sp,
            modifier = Modifier
                .offset(x = 17.dp)
                .align(Alignment.CenterStart),
            textAlign = TextAlign.Center
        )

        // Имя
        Text(
            text = name,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 22.sp,
            letterSpacing = 0.01.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 60.dp)
                .width(173.dp)
                .height(22.dp)
        )

        Text(
            text = points,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 22.sp,
            letterSpacing = 0.01.sp,
            textAlign = TextAlign.End,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = (-17).dp)
                .width(90.dp)
                .height(22.dp)
        )
    }
}

@Composable
fun ExerciseItem(
    title: String,
    emoji: String,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(153.dp)
            .height(117.dp)
            .background(color, RoundedCornerShape(20.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Emoji
            Text(
                text = emoji,
                fontSize = 50.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 18.sp,
                letterSpacing = 0.01.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(130.dp)
                    .height(18.dp)
            )
        }
    }
}