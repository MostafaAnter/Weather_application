package app.anter.core.remote.responses.currentWeatherSearch


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Clouds(
    @Json(name = "all")
    var all: Int?
)