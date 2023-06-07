package com.revomvpandriodapp.app.modules.gasmancontiner.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseFragment
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.constants.ProfileOnboarded
import com.revomvpandriodapp.app.databinding.FragmentGasmancontinerBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.gasmancontiner.`data`.model.GasmanorderRowModel
import com.revomvpandriodapp.app.modules.gasmancontiner.`data`.viewmodel.GasmancontinerVM
import com.revomvpandriodapp.app.modules.retailercoveragearea.ui.RetailercoverageareaActivity
import com.revomvpandriodapp.app.modules.retailersetstock.ui.RetailersetstockActivity
import com.revomvpandriodapp.app.network.models.fetchdetails.FetchDetailsResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class GasmancontinerFragment :
    BaseFragment<FragmentGasmancontinerBinding>(R.layout.fragment_gasmancontiner) {
  private val viewModel: GasmancontinerVM by viewModels<GasmancontinerVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    val gasmanorderAdapter =
    GasmanorderAdapter(viewModel.gasmanorderList.value?:mutableListOf())
    binding.recyclerGasmanorder.adapter = gasmanorderAdapter
    gasmanorderAdapter.setOnItemClickListener(
    object : GasmanorderAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : GasmanorderRowModel) {
        onClickRecyclerGasmanorder(view, position, item)
      }
    }
    )
    viewModel.gasmanorderList.observe(requireActivity()) {
      gasmanorderAdapter.updateData(it)
    }
    binding.gasmancontinerVM = viewModel
    requireActivity().hideKeyboard()
    viewModel.callFetchDetailsApi()
  }

  override fun setUpClicks(): Unit {
    binding.linearColumnsettings.setOnClickListener {
      if(
      viewModel.fetchDetailsLiveData.value?.getSuccessResponse()?.payload?.onBoarded ==
      ProfileOnboarded.NOTONBOARDED
      ) {
        linearColumnsettingsCondition()
      }
      else {
        linearColumnsettingsCondition1()
      }
    }
  }

  fun onClickRecyclerGasmanorder(
    view: View,
    position: Int,
    item: GasmanorderRowModel
  ): Unit {
    when(view.id) {
    }
  }

  override fun addObservers(): Unit {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(requireActivity()) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = requireActivity().showProgressDialog()
      } else {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchDetailsLiveData.observe(requireActivity()) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchDetails(it)
      } else if(it is ErrorResponse) {
        onErrorFetchDetails(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessFetchDetails(response: SuccessResponse<FetchDetailsResponse>): Unit {
    viewModel.bindFetchDetailsResponse(response.data)
  }

  private fun onErrorFetchDetails(exception: Exception): Unit {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
        else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_loading_profile_de)
        requireActivity().alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  private fun linearColumnsettingsCondition(): Unit {
    val destIntent = RetailercoverageareaActivity.getIntent(requireActivity(), null)
    startActivity(destIntent)
    requireActivity().onBackPressed()
  }

  private fun linearColumnsettingsCondition1(): Unit {
    val destIntent = RetailersetstockActivity.getIntent(requireActivity(), null)
    startActivity(destIntent)
    requireActivity().onBackPressed()
  }

  companion object {
    const val TAG: String = "GASMANCONTINER_FRAGMENT"


    fun getInstance(bundle: Bundle?): GasmancontinerFragment {
      val fragment = GasmancontinerFragment()
      fragment.arguments = bundle
      return fragment
    }
  }
}
