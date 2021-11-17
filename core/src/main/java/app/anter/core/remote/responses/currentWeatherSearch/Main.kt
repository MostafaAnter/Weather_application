package app.anter.core.remote.responses.currentWeatherSearch


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Main(
    @Json(name = "temp")
    var temp: Double?,
    @Json(name = "pressure")
    var pressure: Int?,
    @Json(name = "humidity")
    var humidity: Int?,
    @Json(name = "temp_min")
    var tempMin: Double?,
    @Json(name = "temp_max")
    var tempMax: Double?
)