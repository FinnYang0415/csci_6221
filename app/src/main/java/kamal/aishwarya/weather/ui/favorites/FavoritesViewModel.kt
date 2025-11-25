package kamal.aishwarya.weather.ui.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kamal.aishwarya.weather.data.repository.FavoritesRepository
import kamal.aishwarya.weather.model.FavoriteCity
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the user's favorite cities.
 * Observes the FavoritesRepository and exposes favorites as Compose State.
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: FavoritesRepository
) : ViewModel() {

    private val _favoritesState = mutableStateOf<List<FavoriteCity>>(emptyList())
    val favoritesState: State<List<FavoriteCity>> = _favoritesState

    init {
        viewModelScope.launch {
            repository.favorites.collect { favorites ->
                _favoritesState.value = favorites
            }
        }
    }

    /**
     * Adds a new favorite city.
     * @param cityName Name of the city
     * @param country Country of the city
     * @param temp Temperature in Celsius
     * @param description Weather condition description
     */
    fun addFavorite(cityName: String, country: String, temp: Double?, description: String?) {
        val favorite = FavoriteCity(
            name = cityName,
            country = country,
            lastTemperature = temp,
            description = description
        )
        repository.addFavorite(favorite)
    }

    /**
     * Removes a favorite city from the list.
     */
    fun removeFavorite(city: FavoriteCity) {
        repository.removeFavorite(city)
    }
}
