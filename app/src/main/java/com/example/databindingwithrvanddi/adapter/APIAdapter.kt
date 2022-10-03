package com.example.databindingwithrvanddi.adapter

import com.example.databindingwithrvanddi.R
import com.example.databindingwithrvanddi.databinding.ApiDataViewBinding
import com.example.databindingwithrvanddi.model.APIModel

class APIAdapter(
    private val list:List<APIModel>,
    private val clickListener: ClickListener
):BaseAdapter<ApiDataViewBinding,APIModel>(list) {
    override val layoutId: Int
        get() = R.layout.api_data_view

    override fun bind(binding: ApiDataViewBinding, item: APIModel) {
       binding.apply {
           apiData=item
           listener=clickListener
       }
    }

    interface ClickListener{
        fun onItemClick(apiModel: APIModel)
    }

}