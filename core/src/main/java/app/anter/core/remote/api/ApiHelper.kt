package app.anter.core.remote.api

import app.anter.core.remote.responses.currentWeatherSearch.CurrentWeatherSearchResponse
import retrofit2.Response

/**
 * Created by Mostafa Anter on 11/10/20.
 */
interface ApiHelper {
    suspend fun searchCurrentWeather(cityName: String): Response<CurrentWeatherSearchResponse>
}