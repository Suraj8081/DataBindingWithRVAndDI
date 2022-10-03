package com.example.databindingwithrvanddi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.databindingwithrvanddi.adapter.APIAdapter
import com.example.databindingwithrvanddi.databinding.ActivityMainBinding
import com.example.databindingwithrvanddi.model.APIModel
import com.example.databindingwithrvanddi.mvvm.viewModel.APIViewModel
import com.example.databindingwithrvanddi.utils.NetworkResult
import com.example.databindingwithrvanddi.utils.submitList
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),APIAdapter.ClickListener {

    val apiViewModel: APIViewModel by viewModels()
    val TAG = this.javaClass.toString()


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            adapter = APIAdapter(listOf(),this@MainActivity)
        }
        setContentView(binding.root)

        apiViewModel.getAPiData()



        apiViewModel.getApiData.observe(this, androidx.lifecycle.Observer {

            when (it) {
                is NetworkResult.Success -> {
                    Log.d(TAG, "data: ${it.data} message ${it.message} ")
                    submitList(binding.rvApi,it.data!!.entries)
                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Error -> {
                    Log.d(TAG, "onCreate: ${it.message}")
                }
                else -> {
                    Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
                }
            }

        })


    }

    override fun onItemClick(apiModel: APIModel) {
        Toast.makeText(this, "${apiModel.API}", Toast.LENGTH_SHORT).show()
    }
}