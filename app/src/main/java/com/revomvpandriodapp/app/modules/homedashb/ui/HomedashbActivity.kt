package com.revomvpandriodapp.app.modules.homedashb.ui

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
import com.revomvpandriodapp.app.databinding.ActivityHomedashbBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.arealisting.ui.AreaListingActivity
import com.revomvpandriodapp.app.modules.cusorderpage.ui.CusorderpageActivity
import com.revomvpandriodapp.app.modules.homedashb.`data`.model.Gasmanorder1RowModel
import com.revomvpandriodapp.app.modules.homedashb.`data`.viewmodel.HomedashbVM
import com.revomvpandriodapp.app.modules.orderlist4household.ui.Orderlist4householdActivity
import com.revomvpandriodapp.app.network.models.fetchdetails1.FetchDetails1Response
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class HomedashbActivity : BaseActivity<ActivityHomedashbBinding>(R.layout.activity_homedashb) {
  private val viewModel: HomedashbVM by viewModels<HomedashbVM>()

  private val REQUEST_CODE_ORDERLIST4HOUSEHOLD_ACTIVITY: Int = 791

  private val REQUEST_CODE_AREA_LISTING_ACTIVITY: Int = 820

  private val REQUEST_CODE_CUSORDERPAGE_ACTIVITY: Int = 971

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val gasmanorderAdapter =
    GasmanorderAdapter(viewModel.gasmanorderList.value?:mutableListOf())
    binding.recyclerGasmanorder.adapter = gasmanorderAdapter
    gasmanorderAdapter.setOnItemClickListener(
    object : GasmanorderAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Gasmanorder1RowModel) {
        onClickRecyclerGasmanorder(view, position, item)
      }
    }
    )
    viewModel.gasmanorderList.observe(this) {
      gasmanorderAdapter.updateData(it)
    }
    binding.homedashbVM = viewModel
    this@HomedashbActivity.hideKeyboard()
    viewModel.callFetchDetails1Api()
  }

  override fun setUpClicks(): Unit {
    binding.linearColumnvolume.setOnClickListener {
      val destIntent = Orderlist4householdActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_ORDERLIST4HOUSEHOLD_ACTIVITY)
    }
    binding.linearBuyairtime.setOnClickListener {
      val destIntent = AreaListingActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_AREA_LISTING_ACTIVITY)
    }
  }

  fun onClickRecyclerGasmanorder(
    view: View,
    position: Int,
    item: Gasmanorder1RowModel
  ): Unit {
    when(view.id) {
      R.id.btnDetails ->  {
        val destBundle = Bundle()
        destBundle.putString("cusOrderId",Gson().toJson(viewModel.fetchDetailsLiveData.value?.getSuccessResponse()?.payload?.customerOrders?.get(position)?.cusOrderId))
        val destIntent = CusorderpageActivity.getIntent(this, destBundle)
        startActivityForResult(destIntent, REQUEST_CODE_CUSORDERPAGE_ACTIVITY)
      }
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@HomedashbActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@HomedashbActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchDetails1LiveData.observe(this@HomedashbActivity) {
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
        this@HomedashbActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "HOMEDASHB_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, HomedashbActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
