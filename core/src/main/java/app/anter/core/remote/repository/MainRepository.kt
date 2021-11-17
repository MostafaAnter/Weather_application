package app.anter.core.remote.repository

import app.anter.core.remote.api.ApiHelper
import javax.inject.Inject

/**
 * Created by Mostafa Anter on 11/16/21.
 */
class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun searchCurrentWeather(cityName: String) =  apiHelper.searchCurrentWeather(cityName)

}