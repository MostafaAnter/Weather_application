package app.anter.feature_search_current_weather.ui.current_weather_search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.anter.core.remote.responses.currentWeatherSearch.CurrentWeatherSearchResponse
import app.anter.feature_search_current_weather.R
import app.anter.feature_search_current_weather.databinding.ItemWeatherListBinding
import java.util.*

/**
 * A custom adapter to use with the RecyclerView widget.
 */
class WeatherSearchResultListAdapter(
    private val mContext: Context,
    private var modelList: ArrayList<CurrentWeatherSearchResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // call back methods for handle click listeners
    private var mItemClickListener: OnItemClickListener? = null

    fun addData(newList: List<CurrentWeatherSearchResponse>){
        modelList.clear()
        modelList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemWeatherListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.item_weather_list,
                viewGroup,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        //Here you can fill your row view
        if (holder is ViewHolder) {
            val model = getItem(position)
            holder.bind(model)
        }
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int, model: CurrentWeatherSearchResponse?)
    }

    inner class ViewHolder(private val binding: ItemWeatherListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            // handle item click
            binding.root.setOnClickListener {
                mItemClickListener!!.onItemClick(
                    binding.root,
                    bindingAdapterPosition,
                    getItem(bindingAdapterPosition)
                )
            }
        }

        fun bind(model: CurrentWeatherSearchResponse?) {
            binding.model = model
            binding.executePendingBindings()

        }
    }

    private fun getItem(position: Int): CurrentWeatherSearchResponse {
        return modelList[position]
    }

    override fun getItemCount(): Int {
        return modelList.size
    }
}