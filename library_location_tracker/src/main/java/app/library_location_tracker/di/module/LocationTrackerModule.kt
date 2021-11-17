package app.library_location_tracker.di.module

import androidx.fragment.app.Fragment
import app.library_location_tracker.tracker.DeviceLocationTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

/**
 * Created by Mostafa Anter on 12/3/20.
 */
@Module
@InstallIn(FragmentComponent::class)
class LocationTrackerModule {
    @FragmentScoped
    @Provides
    fun providesListener(fragment: Fragment): DeviceLocationTracker.DeviceLocationListener =
        fragment as DeviceLocationTracker.DeviceLocationListener
}