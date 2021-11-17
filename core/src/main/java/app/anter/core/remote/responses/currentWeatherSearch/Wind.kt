package app.anter.core.remote.responses.currentWeatherSearch


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Wind(
    @Json(name = "speed")
    var speed: Double?,
    @Json(name = "deg")
    var deg: Int?
)