package com.revomvpandriodapp.app.modules.dashboard4gasmantwo.ui

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
import com.revomvpandriodapp.app.constants.ProfileOnboarded
import com.revomvpandriodapp.app.databinding.ActivityDashboard4gasmantwoBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.dashboard4gasmantwo.`data`.model.Gasmanorder1RowModel
import com.revomvpandriodapp.app.modules.dashboard4gasmantwo.`data`.viewmodel.Dashboard4gasmantwoVM
import com.revomvpandriodapp.app.modules.noactionordergasmandetail.ui.NoactionordergasmandetailActivity
import com.revomvpandriodapp.app.modules.orderlist4gasman.ui.Orderlist4gasmanActivity
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

class Dashboard4gasmantwoActivity :
    BaseActivity<ActivityDashboard4gasmantwoBinding>(R.layout.activity_dashboard4gasmantwo) {
  private val viewModel: Dashboard4gasmantwoVM by viewModels<Dashboard4gasmantwoVM>()

  private val REQUEST_CODE_RETAILERCOVERAGEAREA_ACTIVITY: Int = 340

  private val REQUEST_CODE_ORDERLIST4GASMAN_ACTIVITY: Int = 713

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
    binding.dashboard4gasmantwoVM = viewModel
    this@Dashboard4gasmantwoActivity.hideKeyboard()
    viewModel.callFetchDetailsApi()
  }

  override fun setUpClicks(): Unit {
    binding.linearBuyairtime.setOnClickListener {
      if(
      viewModel.fetchDetailsLiveData.value?.getSuccessResponse()?.payload?.onBoarded ==
          ProfileOnboarded.NOTONBOARDED
      ) {
        linearBuyairtimeCondition()
      }
      else {
        linearBuyairtimeCondition1()
      }
    }
    binding.txtShowall.setOnClickListener {
      val destIntent = Orderlist4gasmanActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_ORDERLIST4GASMAN_ACTIVITY)
    }
    binding.linearColumnvolume.setOnClickListener {
      val destIntent = Orderlist4gasmanActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_ORDERLIST4GASMAN_ACTIVITY)
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
        destBundle.putString("orderId",Gson().toJson(viewModel.fetchDetailsLiveData.value?.getSuccessResponse()?.payload?.sellerOrders?.get(position)?.orderId))
        val destIntent = NoactionordergasmandetailActivity.getIntent(this, destBundle)
        startActivity(destIntent)
        finish()
      }
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@Dashboard4gasmantwoActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@Dashboard4gasmantwoActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchDetailsLiveData.observe(this@Dashboard4gasmantwoActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchDetails(it)
      } else if(it is ErrorResponse)  {
        onErrorFetchDetails(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessFetchDetails(response: SuccessResponse<FetchDetailsResponse>) {
    viewModel.bindFetchDetailsResponse(response.data)
  }

  private fun onErrorFetchDetails(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_loading_profile_de)
        this@Dashboard4gasmantwoActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  private fun linearBuyairtimeCondition() {
    val destIntent = RetailercoverageareaActivity.getIntent(this, null)
    startActivityForResult(destIntent, REQUEST_CODE_RETAILERCOVERAGEAREA_ACTIVITY)
  }

  private fun linearBuyairtimeCondition1() {
    val destIntent = RetailersetstockActivity.getIntent(this, null)
    startActivity(destIntent)
    finish()
  }

  companion object {
    const val TAG: String = "DASHBOARD4GASMANTWO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, Dashboard4gasmantwoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
