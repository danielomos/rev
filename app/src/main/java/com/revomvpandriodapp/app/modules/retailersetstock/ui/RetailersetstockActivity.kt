package com.revomvpandriodapp.app.modules.retailersetstock.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityRetailersetstockBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.retailersetstock.`data`.viewmodel.RetailersetstockVM
import com.revomvpandriodapp.app.network.models.createupdate.CreateUpdateResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class RetailersetstockActivity :
    BaseActivity<ActivityRetailersetstockBinding>(R.layout.activity_retailersetstock) {
  private val viewModel: RetailersetstockVM by viewModels<RetailersetstockVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.retailersetstockVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnSetStock.setOnClickListener {
      this@RetailersetstockActivity.hideKeyboard()
      viewModel.callCreateUpdateApi()
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@RetailersetstockActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@RetailersetstockActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.createUpdateLiveData.observe(this@RetailersetstockActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreateUpdate(it)
      } else if(it is ErrorResponse)  {
        onErrorCreateUpdate(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessCreateUpdate(response: SuccessResponse<CreateUpdateResponse>) {
    this@RetailersetstockActivity.alert(MyApp.getInstance().getString(R.string.lbl_successful),
    MyApp.getInstance().getString(R.string.msg_stock_updated_succefully)) {
      neutralButton {
      }
    }
    viewModel.bindCreateUpdateResponse(response.data)
  }

  private fun onErrorCreateUpdate(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_updating_stock)
        this@RetailersetstockActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "RETAILERSETSTOCK_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, RetailersetstockActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
