package app.library_location_tracker.tracker

import android.Manifest
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.ref.WeakReference
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by Mostafa Anter on 12/3/20.
 * Use GPS or Network Provider to get device Location
 */
@FragmentScoped
class DeviceLocationTracker @Inject constructor(
    @ActivityContext context: Context,
    fragment: Fragment,
    private var deviceLocationListener: DeviceLocationListener
) : LocationListener, CoroutineScope {

    private var deviceLocation: Location? = null
    private val context: WeakReference<Context> = WeakReference(context)
    private val fragment: WeakReference<Fragment> = WeakReference(fragment)
    private var locationManager: LocationManager? = null
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun initializeLocationProviders() {
        //Init Location Manger if not already initialized
        if (null == locationManager) {
            locationManager = context.get()
                ?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
        locationManager?.apply {
            // flag for GPS status
            val isGPSEnabled = isProviderEnabled(LocationManager.GPS_PROVIDER)
            // flag for network status
            val isNetworkEnabled = isProviderEnabled(LocationManager.PASSIVE_PROVIDER)
            //If we have permission
            if (ActivityCompat.checkSelfPermission(context.get()!!, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context.get()!!, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                //First Try GPS
                if (isGPSEnabled) {
                    requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        UPDATE_FREQUENCY_TIME,
                        UPDATE_FREQUENCY_DISTANCE.toFloat(), this@DeviceLocationTracker)
                    deviceLocation = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                } else {
                    // use google play service to enable gps
                    enableLocationIfRequired()
                }
                //If failed try using NetworkManger
                if(null==deviceLocation && isNetworkEnabled) {
                    requestLocationUpdates(
                        LocationManager.PASSIVE_PROVIDER,
                        0, 0f,
                        this@DeviceLocationTracker)
                    deviceLocation = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                }
            }
        }
    }

    private fun getLastLocation(){
        locationManager?.apply {
            //If we have permission
            if (ActivityCompat.checkSelfPermission(
                    context.get()!!,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    context.get()!!,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    UPDATE_FREQUENCY_TIME,
                    UPDATE_FREQUENCY_DISTANCE.toFloat(), this@DeviceLocationTracker
                )
                deviceLocation =
                    locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            }
        }
    }

    private fun enableLocationIfRequired() {
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY))
            .setAlwaysShow(true)

        val settingsClient = LocationServices.getSettingsClient(context.get() as AppCompatActivity)

        val task = settingsClient!!.checkLocationSettings(builder.build())
        task.addOnCompleteListener {
            try {
                //throw exception if user dos'nt active gps
                it.getResult(ApiException::class.java)
                //Success
                Log.d(javaClass.simpleName, "Location is enabled")
                //get last location
                getLastLocation()

            } catch (exception: ApiException) {
                Log.d(javaClass.simpleName, "exception thrown: ${exception.statusCode}")
                when (exception.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        // Location settings are not satisfied. But could be fixed by showing the
                        // user a dialog.
                        try {
                            // Cast to a resolvable exception.
                            val resolvable = exception as ResolvableApiException
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            Log.d(javaClass.simpleName, "startResolutionForResult called")

                            // to get activity result in fragment
                            fragment.get()!!.startIntentSenderForResult(
                                resolvable.resolution.intentSender,
                                RC_LOCATION_ENABLE,
                                null, 0, 0, 0, null
                            )

                        } catch (e: IntentSender.SendIntentException) {
                            // Ignore the error.
                            Log.d(javaClass.simpleName, "IntentSender.SendIntentException")
                        } catch (e: ClassCastException) {
                            // Ignore, should be an impossible error.
                            Log.d(javaClass.simpleName, "ClassCastException")
                        }
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                    }
                }
            }
        }
    }

    /**
     * Stop using GPS listener
     * Must call this function to stop using GPS
     */
    fun stopUpdate() {
        if (locationManager != null) {
            locationManager!!.removeUpdates(this@DeviceLocationTracker)
        }
    }
    override fun onLocationChanged(newDeviceLocation: Location) {
        deviceLocation = newDeviceLocation
        launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val addressList: List<Address?>?
                try {
                    addressList = Geocoder(context.get(),
                        Locale.ENGLISH).getFromLocation(
                        newDeviceLocation.latitude,
                        newDeviceLocation.longitude,
                        1)
                    deviceLocationListener.onDeviceLocationChanged(addressList)
                    Log.i(TAG, "Fetch address list"+addressList)
                } catch (e: IOException) {
                    Log.e(TAG, "Failed Fetched Address List")
                }
            }
        }
    }
    override fun onProviderDisabled(provider: String) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    interface DeviceLocationListener {
        fun onDeviceLocationChanged(results: List<Address>?)
    }
    companion object {
        // The minimum distance to change Updates in meters
        private const val UPDATE_FREQUENCY_DISTANCE: Long = 1 // 10 meters
        // The minimum time between updates in milliseconds
        private const val UPDATE_FREQUENCY_TIME: Long = 1 // 1 minute
        private val TAG = DeviceLocationTracker::class.java.simpleName
        const val RC_LOCATION_ENABLE = 101
    }
}