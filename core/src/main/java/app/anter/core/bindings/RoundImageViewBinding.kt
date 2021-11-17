package app.anter.core.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import java.io.File

/**
 * Created by Mostafa Anter on 11/25/20.
 */

@BindingAdapter("roundedImageUrl", requireAll = false)
fun ImageView.roundedImageUrl(url: String?) {
    if (url!!.startsWith("http")) {
        load(url) {
            crossfade(true)
            transformations(RoundedCornersTransformation())
        }
    } else {
        load(File(url)) {
            crossfade(true)
            transformations(RoundedCornersTransformation())
        }
    }
}
