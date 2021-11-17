package app.anter.core.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation

/**
 * Created by Mostafa Anter on 11/25/20.
 */

@BindingAdapter("normalImageUri", requireAll = false)
fun ImageView.normalImageUri(imageCode: String?) {
    load("http://openweathermap.org/img/wn/${imageCode}@2x.png") {
        crossfade(true)

    }
}

@BindingAdapter("roundedImageUri", requireAll = false)
fun ImageView.roundedImageUri(uri: String?) {
    load(uri) {
        crossfade(true)
        transformations(RoundedCornersTransformation())
    }
}
