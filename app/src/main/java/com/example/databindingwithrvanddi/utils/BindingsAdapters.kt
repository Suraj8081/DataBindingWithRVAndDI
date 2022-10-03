package com.example.databindingwithrvanddi.utils

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingwithrvanddi.adapter.BaseAdapter

@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: BaseAdapter<ViewDataBinding, Any>?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<Any>?) {
    val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, Any>?
    adapter?.updateData(list ?: listOf())
}



