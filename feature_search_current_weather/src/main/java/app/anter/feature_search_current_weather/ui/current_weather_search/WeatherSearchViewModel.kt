package app.anter.feature_search_current_weather.ui.current_weather_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.anter.core.remote.repository.MainRepository
import app.anter.core.remote.responses.currentWeatherSearch.CurrentWeatherSearchResponse
import app.anter.core.util.NetworkHelper
import app.anter.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherSearchViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper) : ViewModel() {

    private val _searchResult = MutableStateFlow<Resource<CurrentWeatherSearchResponse>>(Resource.firstOpen(null))
    val searchResult: StateFlow<Resource<CurrentWeatherSearchResponse>>
        get() = _searchResult

    fun searchCityWeather(cityName: String) {
        viewModelScope.launch {
            _searchResult.value = Resource.loading(null)
            if (networkHelper.isNetworkConnected()) {
                mainRepository.searchCurrentWeather(cityName).let {
                    if (it.isSuccessful) {
                        _searchResult.value = Resource.success(it.body())
                    } else _searchResult.value = Resource.error(it.errorBody().toString(), null)
                }
            } else _searchResult.value = Resource.error("No internet connection", null)
        }
    }

}