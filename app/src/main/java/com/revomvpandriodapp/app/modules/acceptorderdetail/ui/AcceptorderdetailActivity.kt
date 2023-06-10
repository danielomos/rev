package com.revomvpandriodapp.app.modules.acceptorderdetail.ui

import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityAcceptorderdetailBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.acceptorderdetail.`data`.viewmodel.AcceptorderdetailVM
import com.revomvpandriodapp.app.network.models.createaccept.CreateAcceptResponse
import com.revomvpandriodapp.app.network.models.fetchid.FetchIdResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class AcceptorderdetailActivity :
    BaseActivity<ActivityAcceptorderdetailBinding>(R.layout.activity_acceptorderdetail) {
  private val viewModel: AcceptorderdetailVM by viewModels<AcceptorderdetailVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.acceptorderdetailVM = viewModel
    this@AcceptorderdetailActivity.hideKeyboard()
    viewModel.callFetchIdApi()
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnBackToHomepageOne.setOnClickListener {
      // TODO please, add your navigation code here
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@AcceptorderdetailActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@AcceptorderdetailActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.createAcceptLiveData.observe(this@AcceptorderdetailActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreateAccept(it)
      } else if(it is ErrorResponse)  {
        onErrorCreateAccept(it.data ?:Exception())
      }
    }
    viewModel.fetchIdLiveData.observe(this@AcceptorderdetailActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchId(it)
      } else if(it is ErrorResponse)  {
        onErrorFetchId(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessCreateAccept(response: SuccessResponse<CreateAcceptResponse>) {
    this@AcceptorderdetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_successful),
    MyApp.getInstance().getString(R.string.lbl_order_update)) {
      neutralButton {
      }
    }
    viewModel.bindCreateAcceptResponse(response.data)
  }

  private fun onErrorCreateAccept(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_setting_order_st)
        this@AcceptorderdetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  private fun onSuccessFetchId(response: SuccessResponse<FetchIdResponse>) {
    viewModel.bindFetchIdResponse(response.data)
  }

  private fun onErrorFetchId(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_loading_order_de)
        this@AcceptorderdetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "ACCEPTORDERDETAIL_ACTIVITY"

  }
}
