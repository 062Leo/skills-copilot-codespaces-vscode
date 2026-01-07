package com.example.reactiontime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReactionTimeGame()
        }
    }
}

@Composable
fun ReactionTimeGame() {
    var gameState by remember { mutableStateOf(GameState.WAITING) }
    var backgroundColor by remember { mutableStateOf(Color.Gray) }
    var displayText by remember { mutableStateOf("Tap to start") }
    var startTime by remember { mutableStateOf(0L) }
    var bestTime by remember { mutableStateOf<Long?>(null) }

    LaunchedEffect(gameState) {
        when (gameState) {
            GameState.WAITING -> {
                backgroundColor = Color.Gray
                displayText = "Tap to start"
            }
            GameState.GET_READY -> {
                backgroundColor = Color.Gray
                displayText = "Wait for green..."
                val randomDelay = Random.nextLong(1000, 4000)
                delay(randomDelay)
                if (gameState == GameState.GET_READY) {
                    gameState = GameState.GO
                }
            }
            GameState.GO -> {
                backgroundColor = Color.Green
                displayText = "TAP NOW!"
                startTime = System.currentTimeMillis()
            }
            GameState.TOO_EARLY -> {
                backgroundColor = Color.Red
                displayText = "Too early!\nTap to try again"
            }
            GameState.RESULT -> {
                // Result is handled in onClick
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .clickable {
                when (gameState) {
                    GameState.WAITING -> {
                        gameState = GameState.GET_READY
                    }
                    GameState.GET_READY -> {
                        gameState = GameState.TOO_EARLY
                    }
                    GameState.GO -> {
                        val reactionTime = System.currentTimeMillis() - startTime
                        displayText = buildString {
                            append("${reactionTime}ms")
                            if (bestTime == null || reactionTime < bestTime!!) {
                                bestTime = reactionTime
                                append("\nNew best!")
                            } else {
                                append("\nBest: ${bestTime}ms")
                            }
                            append("\n\nTap to try again")
                        }
                        backgroundColor = Color.Gray
                        gameState = GameState.RESULT
                    }
                    GameState.TOO_EARLY, GameState.RESULT -> {
                        gameState = GameState.WAITING
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = displayText,
            fontSize = 32.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

enum class GameState {
    WAITING,
    GET_READY,
    GO,
    TOO_EARLY,
    RESULT
}
