package app.anter.feature_search_current_weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import app.anter.feature_search_current_weather.databinding.CurrentWeatherSearchActivityBinding
import app.anter.feature_search_current_weather.ui.current_weather_search.CurrentWeatherSearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherSearchActivity : AppCompatActivity() {

    //for bind view with activity
    private lateinit var binding: CurrentWeatherSearchActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.current_weather_search_activity
        )
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CurrentWeatherSearchFragment.newInstance())
                .commitNow()
        }
    }
}