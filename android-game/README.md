# Reaction Time Test - Android Game

A minimal Android game built with Kotlin and Jetpack Compose.

## 📱 Download APK for Mobile

### Option 1: Download from GitHub Actions (Recommended)

1. **Visit the repository on GitHub Mobile or browser**: [Actions Tab](../../actions/workflows/build-android-apk.yml)
2. **Click on the latest successful workflow run** (green checkmark ✓)
3. **Scroll down to "Artifacts"** section
4. **Download** `reaction-test-debug` (this will download a ZIP file)
5. **Extract the ZIP** to get `app-debug.apk`
6. **On your Android device**:
   - Transfer the APK file to your phone (via USB, email, cloud storage, etc.)
   - Go to **Settings → Security → Install from Unknown Sources** and enable it
   - Use a file manager to locate the APK file
   - Tap the APK file to install
   - Tap **Install** when prompted
7. **Launch the app** from your app drawer and enjoy!

### Option 2: Build APK Yourself

If you have Android development tools installed:

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

# Build debug APK (for testing)
./gradlew assembleDebug

# The APK will be located at:
# app/build/outputs/apk/debug/app-debug.apk

# Build release APK (optimized)
./gradlew assembleRelease

# The APK will be located at:
# app/build/outputs/apk/release/app-release-unsigned.apk
```

### Install on Connected Device/Emulator

```bash
# Install debug version
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
