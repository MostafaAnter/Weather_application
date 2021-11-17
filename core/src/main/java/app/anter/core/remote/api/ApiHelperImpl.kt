package app.anter.core.remote.api

import app.anter.core.remote.responses.currentWeatherSearch.CurrentWeatherSearchResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Mostafa Anter on 11/10/20.
 */
class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun searchCurrentWeather(cityName: String): Response<CurrentWeatherSearchResponse> =
        apiService.searchCurrentWeather(cityName)
}