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

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: FavoritesRepository
) : ViewModel() {

    private val _favoritesState = mutableStateOf<List<FavoriteCity>>(emptyList())
    val favoritesState: State<List<FavoriteCity>> = _favoritesState

    init {
        viewModelScope.launch {
            repository.favorites.collect { list ->
                _favoritesState.value = list
            }
        }
    }

    fun addFavorite(cityName: String, country: String, temp: Double?, description: String?) {
        val favorite = FavoriteCity(name = cityName, country = country, lastTemperature = temp, description = description)
        repository.addFavorite(favorite)
    }

    fun removeFavorite(city: FavoriteCity) {
        repository.removeFavorite(city)
    }
}
