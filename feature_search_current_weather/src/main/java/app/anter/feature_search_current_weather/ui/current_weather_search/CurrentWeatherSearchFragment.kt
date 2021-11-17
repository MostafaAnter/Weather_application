package app.anter.feature_search_current_weather.ui.current_weather_search

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.location.LocationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import app.anter.core.remote.responses.currentWeatherSearch.CurrentWeatherSearchResponse
import app.anter.core.util.Status
import app.anter.feature_search_current_weather.R
import app.anter.feature_search_current_weather.databinding.CurrentWeatherSearchFragmentBinding
import app.anter.feature_search_current_weather.ui.usecase.SearchValidator
import app.library_location_tracker.tracker.DeviceLocationTracker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import javax.inject.Inject


@RuntimePermissions
@AndroidEntryPoint
class CurrentWeatherSearchFragment : Fragment(), DeviceLocationTracker.DeviceLocationListener {

    companion object {
        fun newInstance() = CurrentWeatherSearchFragment()
    }

    // init location tracker object
    @Inject
    lateinit var deviceLocationTracker: DeviceLocationTracker

    //inject view model by hilt DI
    private val viewModel: WeatherSearchViewModel by viewModels()

    //for bind view with fragment
    private lateinit var binding: CurrentWeatherSearchFragmentBinding

    //init data for recyclerview
    private var mAdapter: WeatherSearchResultListAdapter? = null
    private val modelList = ArrayList<CurrentWeatherSearchResponse>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.current_weather_search_fragment,
            container,
            false
        )
        return binding.root
    }

    //for avoiding memory leak to improve performance
    override fun onStop() {
        super.onStop()
        //clear view attached with fragment
        binding.unbind()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //prepare recycler view
        setAdapter()

        //handle search button click
        handleSearchAction()

        //for listen to coming data
        setupObserver()

        // request location update after open page
        requestCurrentLocationWithPermissionCheck()

    }

    private fun setAdapter() {
        mAdapter = WeatherSearchResultListAdapter(requireActivity(), modelList)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = mAdapter

        //handle item click
        mAdapter!!.SetOnItemClickListener(object : WeatherSearchResultListAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int, model: CurrentWeatherSearchResponse?) {

            }
        })
    }

    private fun showProgress(){
        // show progress view
        binding.swipeRefreshRecyclerList.isEnabled = true
        binding.swipeRefreshRecyclerList.isRefreshing = true
    }

    private fun hideProgress(){
        //hide progress view
        binding.swipeRefreshRecyclerList.isRefreshing = false
        binding.swipeRefreshRecyclerList.isEnabled = false
    }

    private fun showEmptyView(){
        binding.noDataView.parentView.visibility = View.VISIBLE
    }

    private fun hideEmptyView(){
        binding.noDataView.parentView.visibility = View.GONE
    }

    private fun showRecycler(){
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun hideRecycler(){
        binding.recyclerView.visibility = View.GONE
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.searchResult.collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        hideProgress()
                        if (it.data != null){
                            hideEmptyView()
                            showRecycler()
                            renderList(listOf(it.data!!))
                        }else{
                            hideRecycler()
                            showEmptyView()
                        }
                    }
                    Status.LOADING -> {
                        showProgress()
                    }
                    Status.ERROR -> {
                        hideProgress()
                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                        hideRecycler()
                        showEmptyView()
                    }
                }
            }
        }

    }

    private fun renderList(data: List<CurrentWeatherSearchResponse>) {
        mAdapter!!.addData(data)
    }

    private fun handleSearchAction(){
        binding.searchButton.setOnClickListener {
            val query = binding.enterQueryEditText.text.toString()
            if (SearchValidator().isValidQuery(query))
                search(query)
        }
    }
    private fun search(query: String){
        viewModel.searchCityWeather(query)
    }

    //region pick current location and do first search

    //region request permission for pick location
    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    fun requestCurrentLocation(){
        deviceLocationTracker.initializeLocationProviders()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // NOTE: delegate the permission handling to generated function
        onRequestPermissionsResult(requestCode, grantResults)
    }
    //endregion

    // handle gps enabled status by user
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            DeviceLocationTracker.RC_LOCATION_ENABLE -> {
                if (resultCode == Activity.RESULT_OK) {
                    Log.d(javaClass.simpleName, "Location is enabled by user")
                } else {
                    Log.d(javaClass.simpleName, "Location enable request is cancelled by user")
                }
                val lm = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
                if (LocationManagerCompat.isLocationEnabled(lm)) {
                    Log.d(javaClass.simpleName, "Location is enabled by user")
                    // request location update after
                    requestCurrentLocationWithPermissionCheck()
                } else {
                    Log.d(javaClass.simpleName, "Location enable request is cancelled by user")
                }
            }
        }
    }

    override fun onDeviceLocationChanged(results: List<Address>?) {
        results?.let {
            if (it.isNotEmpty()) {
                val currentLocation = it[0]
                currentLocation.apply {

                    // that what i need ;)
                    binding.enterQueryEditText.setText(adminArea.split(" ").get(0))
                    // search weather once get city name
                    search(adminArea.split(" ").get(0))
                    // stop track location
                    deviceLocationTracker.stopUpdate()




                }
            }
        }

    }

    override fun onPause() {
        super.onPause()
        if(isRemoving){
            binding!!.unbind()
            deviceLocationTracker.stopUpdate()
        }
    }

    //endregion

}