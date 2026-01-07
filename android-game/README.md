# Reaction Time Test - Android Game

A minimal Android game built with Kotlin and Jetpack Compose.

## Game Rules

- **Background starts gray**
- After a random delay (1–4 seconds), the background turns **green**
- Tap the screen as fast as possible when it turns green
- Your reaction time is measured in milliseconds
- If you tap before green → "Too early" message is shown
- Your best reaction time is tracked

## Technical Details

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: Single Activity
- **Dependencies**: Only Android SDK and Jetpack Compose (no external libraries)
- **Interaction**: Tap anywhere on the screen (no buttons)
- **Display**: Fullscreen

## Project Structure

```
android-game/
├── app/
│   ├── build.gradle
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       └── java/com/reactiontest/
│           └── MainActivity.kt
├── build.gradle
├── settings.gradle
├── gradle.properties
└── gradle/wrapper/
    └── gradle-wrapper.properties
```

## Building the App

### Prerequisites
- Android SDK (API 24 or higher)
- JDK 8 or higher

### Build Commands

```bash
cd android-game

# Build the app
./gradlew build

# Install on connected device/emulator
./gradlew installDebug
```

## How to Play

1. Launch the app
2. Tap the screen to start
3. Wait for the background to turn green
4. Tap as quickly as possible when it turns green
5. Your reaction time and best time will be displayed
6. Tap again to play another round

## Implementation Details

The game uses:
- **GameState enum** to manage different states (IDLE, WAITING, GREEN, RESULT, TOO_EARLY)
- **LaunchedEffect** for the random delay before turning green
- **remember** for state management
- **Compose Box** with clickable modifier for fullscreen interaction
- Simple color changes for visual feedback
