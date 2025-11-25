package kamal.aishwarya.weather.model

data class FavoriteCity(
    val name: String,
    val country: String,
    val lastTemperature: Double?,
    val description: String?
)
