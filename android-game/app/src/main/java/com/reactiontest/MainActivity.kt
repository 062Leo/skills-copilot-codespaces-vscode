package com.reactiontest

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReactionGame()
        }
    }
}

@Composable
fun ReactionGame() {
    var gameState by remember { mutableStateOf(GameState.IDLE) }
    var reactionTime by remember { mutableStateOf(0L) }
    var bestTime by remember { mutableStateOf(Long.MAX_VALUE) }
    var startTime by remember { mutableStateOf(0L) }
    var message by remember { mutableStateOf("Tap to start") }

    LaunchedEffect(gameState) {
        when (gameState) {
            GameState.WAITING -> {
                val delayTime = Random.nextLong(1000, 4000)
                delay(delayTime)
                startTime = System.currentTimeMillis()
                gameState = GameState.GREEN
            }
            else -> {}
        }
    }

    val backgroundColor = when (gameState) {
        GameState.IDLE -> Color.Gray
        GameState.WAITING -> Color.Gray
        GameState.GREEN -> Color.Green
        GameState.RESULT -> Color.Gray
        GameState.TOO_EARLY -> Color.Gray
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .clickable {
                when (gameState) {
                    GameState.IDLE -> {
                        message = "Wait for green..."
                        gameState = GameState.WAITING
                    }
                    GameState.WAITING -> {
                        message = "Too early!\nTap to try again"
                        gameState = GameState.TOO_EARLY
                    }
                    GameState.GREEN -> {
                        reactionTime = System.currentTimeMillis() - startTime
                        if (reactionTime < bestTime) {
                            bestTime = reactionTime
                        }
                        message = "${reactionTime}ms\nBest: ${if (bestTime == Long.MAX_VALUE) "-" else "${bestTime}ms"}\nTap to try again"
                        gameState = GameState.RESULT
                    }
                    GameState.RESULT, GameState.TOO_EARLY -> {
                        message = "Wait for green..."
                        gameState = GameState.WAITING
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

enum class GameState {
    IDLE,
    WAITING,
    GREEN,
    RESULT,
    TOO_EARLY
}
