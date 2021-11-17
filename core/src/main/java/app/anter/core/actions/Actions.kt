package app.anter.core.actions

import android.content.Context
import android.content.Intent
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Mostafa Anter on 11/19/20.
 */
@Singleton
class Actions @Inject constructor() {
    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)

    //open search news feature
    fun openSearchCurrentWeatherIntent(context: Context) = internalIntent(context, "app.anter.feature_search_current_weather.open")
}