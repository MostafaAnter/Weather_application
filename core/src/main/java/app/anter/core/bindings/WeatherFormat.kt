package app.anter.core.bindings

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Mostafa Anter on 11/17/21.
 */
@BindingAdapter("formatWeather", requireAll = false)
fun TextView.formatWeather(temp: Double) {
    val df = DecimalFormat("#.##")
    val newTemp = temp - 273.15

    text = "${df.format(newTemp)}℃"
}

@BindingAdapter("feelLike", requireAll = false)
fun TextView.feelLike(temp: Double) {
    val df = DecimalFormat("#.##")
    val newTemp = temp - 273.15

    text = "Feels Like ${df.format(newTemp)}℃"
}

@BindingAdapter("cityName", "date", requireAll = false)
fun TextView.cityAndDate(cityName: String, date: Long) {
    val d = Date(date)
    val sdf = SimpleDateFormat("MMM dd, yyyy")

    text = "${cityName}, ${sdf.format(d)}"
}