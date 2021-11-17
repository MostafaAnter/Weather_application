package app.anter.core.remote.responses.currentWeatherSearch


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Coord(
    @Json(name = "lon")
    var lon: Double?,
    @Json(name = "lat")
    var lat: Double?
)