package com.revomvpandriodapp.app.modules.orderlist4gasman.ui

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
import com.revomvpandriodapp.app.databinding.ActivityOrderlist4gasmanBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.noactionordergasmandetail.ui.NoactionordergasmandetailActivity
import com.revomvpandriodapp.app.modules.orderlist4gasman.`data`.model.GasmanmainordrRowModel
import com.revomvpandriodapp.app.modules.orderlist4gasman.`data`.viewmodel.Orderlist4gasmanVM
import com.revomvpandriodapp.app.network.models.fetchall.FetchAllResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class Orderlist4gasmanActivity :
    BaseActivity<ActivityOrderlist4gasmanBinding>(R.layout.activity_orderlist4gasman) {
  private val viewModel: Orderlist4gasmanVM by viewModels<Orderlist4gasmanVM>()

  private val REQUEST_CODE_NOACTIONORDERGASMANDETAIL_ACTIVITY: Int = 621


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val gasmanmainordrAdapter =
    GasmanmainordrAdapter(viewModel.gasmanmainordrList.value?:mutableListOf())
    binding.recyclerGasmanmainordr.adapter = gasmanmainordrAdapter
    gasmanmainordrAdapter.setOnItemClickListener(
    object : GasmanmainordrAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : GasmanmainordrRowModel) {
        onClickRecyclerGasmanmainordr(view, position, item)
      }
    }
    )
    viewModel.gasmanmainordrList.observe(this) {
      gasmanmainordrAdapter.updateData(it)
    }
    binding.orderlist4gasmanVM = viewModel
    this@Orderlist4gasmanActivity.hideKeyboard()
    viewModel.callFetchAllApi()
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerGasmanmainordr(
    view: View,
    position: Int,
    item: GasmanmainordrRowModel
  ): Unit {
    when(view.id) {
      R.id.btnDetails -> {
        val destBundle = Bundle()
        destBundle.putString("orderId",Gson().toJson(viewModel.fetchAllLiveData.value?.getSuccessResponse()?.payload?.get(position)?.orderId))
        val destIntent = NoactionordergasmandetailActivity.getIntent(this, destBundle)
        startActivityForResult(destIntent, REQUEST_CODE_NOACTIONORDERGASMANDETAIL_ACTIVITY)
      }
    }
  }

  override fun addObservers(): Unit {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@Orderlist4gasmanActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@Orderlist4gasmanActivity.showProgressDialog()
      } else {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchAllLiveData.observe(this@Orderlist4gasmanActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchAll(it)
      } else if(it is ErrorResponse) {
        onErrorFetchAll(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessFetchAll(response: SuccessResponse<FetchAllResponse>): Unit {
    viewModel.bindFetchAllResponse(response.data)
  }

  private fun onErrorFetchAll(exception: Exception): Unit {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
        else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_loading_order_hi)
        this@Orderlist4gasmanActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "ORDERLIST4GASMAN_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, Orderlist4gasmanActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
