package com.revomvpandriodapp.app.modules.arealisting.ui

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
import com.revomvpandriodapp.app.databinding.ActivityAreaListingBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.arealisting.`data`.model.ListlanguageRowModel
import com.revomvpandriodapp.app.modules.arealisting.`data`.viewmodel.AreaListingVM
import com.revomvpandriodapp.app.modules.suplist.ui.SuplistActivity
import com.revomvpandriodapp.app.network.models.fetchareas.FetchAreasResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class AreaListingActivity : BaseActivity<ActivityAreaListingBinding>(R.layout.activity_area_listing)
    {
  private val viewModel: AreaListingVM by viewModels<AreaListingVM>()

  private val REQUEST_CODE_SUPLIST_ACTIVITY: Int = 947


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listlanguageAdapter =
    ListlanguageAdapter(viewModel.listlanguageList.value?:mutableListOf())
    binding.recyclerListlanguage.adapter = listlanguageAdapter
    listlanguageAdapter.setOnItemClickListener(
    object : ListlanguageAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListlanguageRowModel) {
        onClickRecyclerListlanguage(view, position, item)
      }
    }
    )
    viewModel.listlanguageList.observe(this) {
      listlanguageAdapter.updateData(it)
    }
    binding.areaListingVM = viewModel
    this@AreaListingActivity.hideKeyboard()
    viewModel.callFetchAreasApi()
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerListlanguage(
    view: View,
    position: Int,
    item: ListlanguageRowModel
  ): Unit {
    when(view.id) {
      R.id.txtLanguage -> {
        val destBundle = Bundle()
        destBundle.putString("areaName",Gson().toJson(viewModel.fetchAreasLiveData.value?.getSuccessResponse()?.payload?.get(position)?.areaName))
        val destIntent = SuplistActivity.getIntent(this, destBundle)
        startActivityForResult(destIntent, REQUEST_CODE_SUPLIST_ACTIVITY)
      }
    }
  }

  override fun addObservers(): Unit {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@AreaListingActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@AreaListingActivity.showProgressDialog()
      } else {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchAreasLiveData.observe(this@AreaListingActivity) {
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
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_loading_available_area)
        this@AreaListingActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "AREA_LISTING_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, AreaListingActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
