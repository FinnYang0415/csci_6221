package kamal.aishwarya.weather.model

/**
 * Represents a favorite city stored by the user.
 * @param name City name
 * @param country Country name
 * @param lastTemperature Last known temperature in Celsius (nullable)
 * @param description Weather description or notes (nullable)
 */
data class FavoriteCity(
    val name: String,
    val country: String,
    val lastTemperature: Double?,
    val description: String?
)
