package kamal.aishwarya.weather.ui.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kamal.aishwarya.weather.model.FavoriteCity
import androidx.compose.ui.tooling.preview.Preview
import kamal.aishwarya.weather.ui.theme.WeatherTheme


@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val favorites by viewModel.favoritesState

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        FavoritesContent(favorites = favorites, modifier = modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesPreview() {
    val sample = listOf(
        FavoriteCity("Munich", "Germany", 19.0, "Cloudy"),
        FavoriteCity("Paris", "France", 22.0, "Sunny"),
        FavoriteCity("Tokyo", "Japan", null, null),
    )
    WeatherTheme {
        FavoritesContent(favorites = sample)
    }
}

@Composable
fun FavoritesContent(
    favorites: List<FavoriteCity>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize().padding(12.dp)) {
        // Display favorites in a simple Column to avoid nesting scrollables.
        // The parent weather screen already has verticalScroll; nested scrollables cause runtime errors.
        if (favorites.isEmpty()) {
            Text(
                text = "No favorite cities yet. Add one from the weather screen!",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            favorites.forEach { city ->
                FavoriteRow(city = city)
            }
        }
    }
}

@Composable
private fun FavoriteRow(city: FavoriteCity) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "${city.name}, ${city.country}", style = MaterialTheme.typography.titleMedium)
            val tempText = city.lastTemperature?.let { "${it}°C" } ?: "N/A"
            Text(text = "Temperature: $tempText", style = MaterialTheme.typography.bodyMedium)
            city.description?.let {
                Text(text = it, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
