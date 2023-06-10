package com.revomvpandriodapp.app.modules.retailercoveragearea.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityRetailercoverageareaBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.retailercoveragearea.`data`.model.AreaslistRowModel
import com.revomvpandriodapp.app.modules.retailercoveragearea.`data`.viewmodel.RetailercoverageareaVM
import com.revomvpandriodapp.app.modules.retailersetup.ui.RetailerSetupActivity
import com.revomvpandriodapp.app.network.models.fetchareas.FetchAreasResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class RetailercoverageareaActivity :
    BaseActivity<ActivityRetailercoverageareaBinding>(R.layout.activity_retailercoveragearea) {
  private val viewModel: RetailercoverageareaVM by viewModels<RetailercoverageareaVM>()

  private val REQUEST_CODE_RETAILER_SETUP_ACTIVITY: Int = 481


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val areaslistAdapter = AreaslistAdapter(viewModel.areaslistList.value?:mutableListOf())
    binding.recyclerAreaslist.adapter = areaslistAdapter
    areaslistAdapter.setOnItemClickListener(
    object : AreaslistAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : AreaslistRowModel) {
        onClickRecyclerAreaslist(view, position, item)
      }
    }
    )
    viewModel.areaslistList.observe(this) {
      areaslistAdapter.updateData(it)
    }
    binding.retailercoverageareaVM = viewModel
    this@RetailercoverageareaActivity.hideKeyboard()
    viewModel.callFetchAreasApi()
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerAreaslist(
    view: View,
    position: Int,
    item: AreaslistRowModel
  ): Unit {
    when(view.id) {
      R.id.txtLanguage -> {
        val destBundle = Bundle()
        destBundle.putString("areaName",Gson().toJson(viewModel.fetchAreasLiveData.value?.getSuccessResponse()?.payload?.get(position)?.areaName))
        val destIntent = RetailerSetupActivity.getIntent(this, destBundle)
        startActivityForResult(destIntent, REQUEST_CODE_RETAILER_SETUP_ACTIVITY)
      }
    }
  }

  override fun addObservers(): Unit {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@RetailercoverageareaActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@RetailercoverageareaActivity.showProgressDialog()
      } else {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchAreasLiveData.observe(this@RetailercoverageareaActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchAreas(it)
      } else if(it is ErrorResponse) {
        onErrorFetchAreas(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessFetchAreas(response: SuccessResponse<FetchAreasResponse>): Unit {
    viewModel.bindFetchAreasResponse(response.data)
  }

  private fun onErrorFetchAreas(exception: Exception): Unit {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
        else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_loading_business_area)
        this@RetailercoverageareaActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "RETAILERCOVERAGEAREA_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, RetailercoverageareaActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
