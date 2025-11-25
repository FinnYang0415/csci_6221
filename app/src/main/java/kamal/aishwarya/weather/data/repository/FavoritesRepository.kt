package kamal.aishwarya.weather.data.repository

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kamal.aishwarya.weather.model.FavoriteCity

@Singleton
class FavoritesRepository @Inject constructor() {
    private val _favorites = MutableStateFlow<List<FavoriteCity>>(emptyList())
    val favorites: StateFlow<List<FavoriteCity>> = _favorites.asStateFlow()

    fun addFavorite(city: FavoriteCity) {
        _favorites.value = _favorites.value + city
    }

    fun removeFavorite(city: FavoriteCity) {
        _favorites.value = _favorites.value.filterNot { it == city }
    }

    fun clear() {
        _favorites.value = emptyList()
    }
}
