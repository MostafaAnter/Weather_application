package app.anter.weatherapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.anter.core.actions.Actions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // inject actions to navigate between  modules
    @Inject
    lateinit var actions: Actions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // navigate to news search feature
        startActivity(
            actions.openSearchCurrentWeatherIntent(
                this
            )
        )
        finish()
    }
}