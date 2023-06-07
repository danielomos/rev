package com.revomvpandriodapp.app.modules.gasmanorderslist.ui

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
import com.revomvpandriodapp.app.databinding.ActivityGasmanorderslistBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.gasmanorderpage.ui.GasmanorderpageActivity
import com.revomvpandriodapp.app.modules.gasmanorderslist.`data`.model.GasmanmainordrRowModel
import com.revomvpandriodapp.app.modules.gasmanorderslist.`data`.viewmodel.GasmanorderslistVM
import com.revomvpandriodapp.app.network.models.fetchall.FetchAllResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class GasmanorderslistActivity :
    BaseActivity<ActivityGasmanorderslistBinding>(R.layout.activity_gasmanorderslist) {
  private val viewModel: GasmanorderslistVM by viewModels<GasmanorderslistVM>()

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
    binding.gasmanorderslistVM = viewModel
    this@GasmanorderslistActivity.hideKeyboard()
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
        val destIntent = GasmanorderpageActivity.getIntent(this, destBundle)
        startActivity(destIntent)
      }
    }
  }

  override fun addObservers(): Unit {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@GasmanorderslistActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@GasmanorderslistActivity.showProgressDialog()
      } else {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchAllLiveData.observe(this@GasmanorderslistActivity) {
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
        this@GasmanorderslistActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "GASMANORDERSLIST_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, GasmanorderslistActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
