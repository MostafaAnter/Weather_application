package app.anter.core.remote.responses.currentWeatherSearch


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Weather(
    @Json(name = "id")
    var id: Int?,
    @Json(name = "main")
    var main: String?,
    @Json(name = "description")
    var description: String?,
    @Json(name = "icon")
    var icon: String?
)