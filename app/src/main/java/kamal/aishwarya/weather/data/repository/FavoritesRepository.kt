package kamal.aishwarya.weather.data.repository

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kamal.aishwarya.weather.model.FavoriteCity

/**
 * Repository for managing user's favorite cities.
 * Uses an in-memory StateFlow to store favorites.
 */
@Singleton
class FavoritesRepository @Inject constructor() {
    private val _favorites = MutableStateFlow<List<FavoriteCity>>(emptyList())
    val favorites: StateFlow<List<FavoriteCity>> = _favorites.asStateFlow()

    /**
     * Adds a city to the favorites list.
     */
    fun addFavorite(city: FavoriteCity) {
        _favorites.value = _favorites.value + city
    }

    /**
     * Removes a city from the favorites list.
     */
    fun removeFavorite(city: FavoriteCity) {
        _favorites.value = _favorites.value.filterNot { it == city }
    }

    /**
     * Clears all favorites.
     */
    fun clear() {
        _favorites.value = emptyList()
    }
}
