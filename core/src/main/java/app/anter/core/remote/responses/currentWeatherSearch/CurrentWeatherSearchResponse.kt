package app.anter.core.remote.responses.currentWeatherSearch


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CurrentWeatherSearchResponse(
    @Json(name = "coord")
    var coord: Coord?,
    @Json(name = "weather")
    var weather: List<Weather>?,
    @Json(name = "base")
    var base: String?,
    @Json(name = "main")
    var main: Main?,
    @Json(name = "visibility")
    var visibility: Int?,
    @Json(name = "wind")
    var wind: Wind?,
    @Json(name = "clouds")
    var clouds: Clouds?,
    @Json(name = "dt")
    var dt: Int?,
    @Json(name = "sys")
    var sys: Sys?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "cod")
    var cod: Int?
)