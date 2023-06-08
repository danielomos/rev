package com.revomvpandriodapp.app.modules.gasmandashb.ui

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
import com.revomvpandriodapp.app.databinding.ActivityGasmandashbBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.gasmandashb.`data`.model.GasmanorderRowModel
import com.revomvpandriodapp.app.modules.gasmandashb.`data`.model.ListcalculatorRowModel
import com.revomvpandriodapp.app.modules.gasmandashb.`data`.viewmodel.GasmandashbVM
import com.revomvpandriodapp.app.modules.gasmanorderpage.ui.GasmanorderpageActivity
import com.revomvpandriodapp.app.modules.orderlist4gasman.ui.Orderlist4gasmanActivity
import com.revomvpandriodapp.app.modules.retailercoveragearea.ui.RetailercoverageareaActivity
import com.revomvpandriodapp.app.network.models.fetchdetails.FetchDetailsResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class GasmandashbActivity : BaseActivity<ActivityGasmandashbBinding>(R.layout.activity_gasmandashb)
    {
  private val viewModel: GasmandashbVM by viewModels<GasmandashbVM>()

  private val REQUEST_CODE_ORDERLIST4GASMAN_ACTIVITY: Int = 683

  private val REQUEST_CODE_RETAILERCOVERAGEAREA_ACTIVITY: Int = 159

  private val REQUEST_CODE_GASMANORDERPAGE_ACTIVITY: Int = 509

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listcalculatorAdapter =
    ListcalculatorAdapter(viewModel.listcalculatorList.value?:mutableListOf())
    binding.recyclerListcalculator.adapter = listcalculatorAdapter
    listcalculatorAdapter.setOnItemClickListener(
    object : ListcalculatorAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListcalculatorRowModel) {
        onClickRecyclerListcalculator(view, position, item)
      }
    }
    )
    viewModel.listcalculatorList.observe(this) {
      listcalculatorAdapter.updateData(it)
    }
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
    viewModel.gasmanorderList.observe(this) {
      gasmanorderAdapter.updateData(it)
    }
    binding.gasmandashbVM = viewModel
    this@GasmandashbActivity.hideKeyboard()
    viewModel.callFetchDetailsApi()
  }

  override fun setUpClicks(): Unit {
    binding.linearColumnvolume.setOnClickListener {
      val destIntent = Orderlist4gasmanActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_ORDERLIST4GASMAN_ACTIVITY)
    }
  }

  fun onClickRecyclerListcalculator(
    view: View,
    position: Int,
    item: ListcalculatorRowModel
  ): Unit {
    when(view.id) {
      R.id.linearPaybill ->  {
        val destIntent = RetailercoverageareaActivity.getIntent(this, null)
        startActivityForResult(destIntent, REQUEST_CODE_RETAILERCOVERAGEAREA_ACTIVITY)
      }
    }
  }

  fun onClickRecyclerGasmanorder(
    view: View,
    position: Int,
    item: GasmanorderRowModel
  ): Unit {
    when(view.id) {
      R.id.btnDetails ->  {
        val destBundle = Bundle()
        destBundle.putString("orderId",Gson().toJson(viewModel.fetchDetailsLiveData.value?.getSuccessResponse()?.payload?.sellerOrders?.get(position)?.orderId))
        val destIntent = GasmanorderpageActivity.getIntent(this, destBundle)
        startActivityForResult(destIntent, REQUEST_CODE_GASMANORDERPAGE_ACTIVITY)
      }
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@GasmandashbActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@GasmandashbActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchDetailsLiveData.observe(this@GasmandashbActivity) {
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
        this@GasmandashbActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "GASMANDASHB_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, GasmandashbActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
