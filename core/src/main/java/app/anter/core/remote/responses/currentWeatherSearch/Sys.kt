package app.anter.core.remote.responses.currentWeatherSearch


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Sys(
    @Json(name = "type")
    var type: Int?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "message")
    var message: Double?,
    @Json(name = "country")
    var country: String?,
    @Json(name = "sunrise")
    var sunrise: Int?,
    @Json(name = "sunset")
    var sunset: Int?
)