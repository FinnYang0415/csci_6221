# Weather

Lightweight Android weather app built with Jetpack Compose.

This project fetches weather data for a city and displays current conditions, hourly forecasts, and daily forecasts. A new "Favorites" feature lets you store favorite cities in memory for quick access.

## ✨ Features

- **Weather Search**: Search for any city and view real-time weather data
- **Current Conditions**: Temperature, humidity, wind speed, UV index, sunrise/sunset times
- **Hourly Forecast**: 24-hour weather breakdown with temperature and conditions
- **Daily Forecast**: 10-day forecast view
- **Favorites**: Add cities to your in-memory favorites list for quick reference
- **Clean UI**: Modern Jetpack Compose interface with smooth animations

## ✅ Full feature list (detailed)

- Weather search: search any city from the top app bar and load current weather.
- Current conditions: shows temperature, feels-like, wind, humidity, UV index and an icon/description of the weather.
- Hourly forecast: scrollable hourly list for the selected day.
- Daily forecast: compact daily cards for upcoming days with min/max temperatures.
- Favorites (add/remove):
	- Add a favorite from the weather screen with "Add to favorites".
	- Open the inline favorites panel with "Show favorites".
	- Click any favorite city in the list to immediately load that city's weather into the main screen (this calls `WeatherViewModel.getWeather(cityName)` and closes the favorites panel).
	- Favorites are stored in-memory for the session (not persisted across app restarts).
- Lightweight navigation: the app updates the main weather screen when selecting favorites (no full navigation stack change by default).
- Compose previews: many screens include `@Preview` implementations for quick Android Studio previewing.

## 🆕 New: Favorites Feature

- Add cities to a favorites list with one tap
- View favorites inline on the weather screen
- Store city name, country, last known temperature, and weather description
- In-memory storage (persisted within the app session)

## Quick start

**Prerequisites:**
- JDK 11 or higher
- Android Studio (Arctic Fox or later)

**Build and run:**

```powershell
# From the project root
.\gradlew.bat clean
.\gradlew.bat assembleDebug
```

Then run the app on an emulator or device via Android Studio.

**Usage:**
1. Launch the app — it loads Munich's weather by default
2. Use the search icon to find any city
3. Tap "Add to favorites" to save the city
4. Tap "Show favorites" to view your saved cities
5. Tap a city in the favorites list to instantly load its weather on the main screen

## 📐 Architecture

This app follows **MVVM** (Model-View-ViewModel) with a **Repository** pattern:

- **Model**: Data classes representing weather entities (e.g., `Weather`, `FavoriteCity`)
- **View**: Jetpack Compose UI components
- **ViewModel**: `WeatherViewModel` and `FavoritesViewModel` manage state via `StateFlow` and Compose `State`
- **Repository**: `DefaultWeatherRepository` (API calls), `FavoritesRepository` (in-memory favorites storage)

Data flow:
1. User searches for a city via the UI
2. `WeatherViewModel` calls `WeatherRepository` to fetch data from OpenWeatherMap API
3. Data is exposed via `StateFlow` and collected by Compose
4. UI updates reactively
5. Favorites are stored/retrieved via `FavoritesRepository` and `FavoritesViewModel`

## 🛠 Tech Stack

- **Language**: [Kotlin](https://developer.android.com/kotlin)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Architecture**: MVVM + Repository Pattern
- **Dependency Injection**: [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Async**: [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) and [Flow](https://developer.android.com/kotlin/flow)
- **Networking**: [Retrofit](https://square.github.io/retrofit), [OkHttp](https://github.com/square/okhttp), [GSON](https://github.com/google/gson)
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/compose/)
- **Logging**: [Timber](https://github.com/JakeWharton/timber)
- **Testing**: [Mockk](https://github.com/mockk/mockk), [Turbine](https://github.com/cashapp/turbine)

## 📦 Project Structure

```
app/
├── src/main/java/kamal/aishwarya/weather/
│   ├── data/
│   │   ├── model/          # API response models
│   │   ├── network/        # Retrofit API interface
│   │   └── repository/     # Data repositories
│   ├── model/              # Domain models (Weather, FavoriteCity, etc.)
│   ├── ui/
│   │   ├── weather/        # Weather screen and ViewModel
│   │   ├── favorites/      # Favorites screen and ViewModel
│   │   ├── theme/          # Compose theme
│   │   └── components/     # Reusable UI components
│   ├── utils/              # Helper utilities
│   ├── di/                 # Hilt dependency injection modules
│   ├── MainActivity.kt
│   └── WeatherApplication.kt
├── build.gradle.kts
└── AndroidManifest.xml
```

## 🎯 Next Steps & Improvements

- **Persistence**: Migrate favorites from in-memory to Room/DataStore for persistence across app restarts
- **Navigation**: Add proper Jetpack Navigation with dedicated favorites screen
- **UI Enhancements**: Favorites removal, reordering, and batch operations
- **Offline Mode**: Cache weather data locally for offline viewing
- **Testing**: Expand unit and integration tests

## 📄 License

This project is provided as-is. Original architecture inspired by community best practices.




