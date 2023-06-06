package com.revomvpandriodapp.app.modules.householddashboardhome.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseFragment
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.FragmentHouseholddashboardHomeBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.arealisting.ui.AreaListingActivity
import com.revomvpandriodapp.app.modules.householddashboardhome.`data`.model.CustomersorderRowModel
import com.revomvpandriodapp.app.modules.householddashboardhome.`data`.viewmodel.HouseholddashboardHomeVM
import com.revomvpandriodapp.app.network.models.fetchdetails1.FetchDetails1Response
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class HouseholddashboardHomeFragment :
    BaseFragment<FragmentHouseholddashboardHomeBinding>(R.layout.fragment_householddashboard_home) {
  private val viewModel: HouseholddashboardHomeVM by viewModels<HouseholddashboardHomeVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    val customersorderAdapter =
    CustomersorderAdapter(viewModel.customersorderList.value?:mutableListOf())
    binding.recyclerCustomersorder.adapter = customersorderAdapter
    customersorderAdapter.setOnItemClickListener(
    object : CustomersorderAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : CustomersorderRowModel) {
        onClickRecyclerCustomersorder(view, position, item)
      }
    }
    )
    viewModel.customersorderList.observe(requireActivity()) {
      customersorderAdapter.updateData(it)
    }
    binding.householddashboardHomeVM = viewModel
    requireActivity().hideKeyboard()
    viewModel.callFetchDetails1Api()
  }

  override fun setUpClicks(): Unit {
    binding.linearColumnsettings.setOnClickListener {
      val destIntent = AreaListingActivity.getIntent(requireActivity(), null)
      startActivity(destIntent)
      requireActivity().onBackPressed()
    }
  }

  fun onClickRecyclerCustomersorder(
    view: View,
    position: Int,
    item: CustomersorderRowModel
  ): Unit {
    when(view.id) {
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(requireActivity()) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = requireActivity().showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchDetails1LiveData.observe(requireActivity()) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchDetails1(it)
      } else if(it is ErrorResponse)  {
        onErrorFetchDetails1(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessFetchDetails1(response: SuccessResponse<FetchDetails1Response>) {
    viewModel.bindFetchDetails1Response(response.data)
  }

  private fun onErrorFetchDetails1(exception: Exception) {
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

  companion object {
    const val TAG: String = "HOUSEHOLDDASHBOARD_HOME_FRAGMENT"


    fun getInstance(bundle: Bundle?): HouseholddashboardHomeFragment {
      val fragment = HouseholddashboardHomeFragment()
      fragment.arguments = bundle
      return fragment
    }
  }
}
