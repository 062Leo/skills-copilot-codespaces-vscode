# Reaction Time Game

A minimal Android game built with Kotlin and Jetpack Compose to test your reaction time.

## Game Concept

Test how fast you can react when the screen turns green!

## Rules

- Tap the gray screen to start
- Wait for the background to turn green (random delay 1-4 seconds)
- Tap as fast as possible when it turns green
- Your reaction time in milliseconds is displayed
- If you tap before the green screen appears, you'll see "Too early"
- The game tracks your best reaction time

## Technical Details

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Target SDK**: 34 (Android 14)
- **Minimum SDK**: 24 (Android 7.0)
- **Architecture**: Single Activity
- **Features**:
  - Fullscreen single screen design
  - No buttons - tap anywhere on the screen
  - Clean and minimal code
  - No external libraries beyond Android/Compose essentials

## Building

To build this app:

```bash
cd ReactionTimeGame
./gradlew build
```

To install on a device or emulator:

```bash
./gradlew installDebug
```

## Project Structure

```
ReactionTimeGame/
├── app/
│   ├── build.gradle.kts          # App-level build configuration
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           └── java/com/example/reactiontime/
│               └── MainActivity.kt  # Main game logic
├── build.gradle.kts               # Project-level build configuration
├── settings.gradle.kts            # Gradle settings
└── gradle.properties              # Gradle properties
```

## How It Works

The game uses Compose state management and coroutines:

1. **State Management**: Uses `remember` and `mutableStateOf` to track game state
2. **Timing**: Uses `System.currentTimeMillis()` for precise reaction time measurement
3. **Random Delay**: Uses `Random.nextLong(1000, 4000)` for 1-4 second delays
4. **Coroutines**: Uses `LaunchedEffect` with `delay()` for timing logic
5. **Game States**: WAITING → GET_READY → GO → RESULT (or TOO_EARLY)

## Code Highlights

- **No Buttons**: The entire screen is clickable using `Modifier.clickable`
- **Fullscreen**: Manifest uses `Theme.DeviceDefault.NoActionBar.Fullscreen`
- **Background Color Changes**: Uses `Modifier.background(backgroundColor)` with dynamic color state
- **Best Time Tracking**: Persists in memory during the session
