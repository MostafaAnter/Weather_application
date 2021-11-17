package app.anter.core.remote.api

import app.anter.core.BuildConfig
import app.anter.core.remote.responses.currentWeatherSearch.CurrentWeatherSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mostafa Anter on 11/10/20.
 */
interface ApiService {
    @GET("/data/2.5/weather")
    suspend fun searchCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = BuildConfig.API_KYE
        ): Response<CurrentWeatherSearchResponse>
}