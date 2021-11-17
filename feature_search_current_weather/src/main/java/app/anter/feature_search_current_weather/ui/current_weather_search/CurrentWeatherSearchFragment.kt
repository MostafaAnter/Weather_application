package app.anter.feature_search_current_weather.ui.current_weather_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CurrentWeatherSearchFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentWeatherSearchFragment()
    }

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

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.searchResult.collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        hideProgress()
                        if (it.data != null){
                            hideEmptyView()
                            renderList(listOf(it.data!!))
                        }else{
                            showEmptyView()
                        }
                    }
                    Status.LOADING -> {
                        showProgress()
                    }
                    Status.ERROR -> {
                        hideProgress()
                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
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

}