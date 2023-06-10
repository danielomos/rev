package com.revomvpandriodapp.app.modules.pickorderdetail.ui

import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityPickorderdetailBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.pickorderdetail.`data`.viewmodel.PickorderdetailVM
import com.revomvpandriodapp.app.network.models.createpickup.CreatePickupResponse
import com.revomvpandriodapp.app.network.models.fetchid.FetchIdResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class PickorderdetailActivity :
    BaseActivity<ActivityPickorderdetailBinding>(R.layout.activity_pickorderdetail) {
  private val viewModel: PickorderdetailVM by viewModels<PickorderdetailVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.pickorderdetailVM = viewModel
    this@PickorderdetailActivity.hideKeyboard()
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
    viewModel.progressLiveData.observe(this@PickorderdetailActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@PickorderdetailActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.createPickupLiveData.observe(this@PickorderdetailActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreatePickup(it)
      } else if(it is ErrorResponse)  {
        onErrorCreatePickup(it.data ?:Exception())
      }
    }
    viewModel.fetchIdLiveData.observe(this@PickorderdetailActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchId(it)
      } else if(it is ErrorResponse)  {
        onErrorFetchId(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessCreatePickup(response: SuccessResponse<CreatePickupResponse>) {
    this@PickorderdetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_successful),
    MyApp.getInstance().getString(R.string.msg_pickup_weight_set_su)) {
      neutralButton {
      }
    }
    viewModel.bindCreatePickupResponse(response.data)
  }

  private fun onErrorCreatePickup(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_setting_pickup_we)
        this@PickorderdetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
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
        this@PickorderdetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "PICKORDERDETAIL_ACTIVITY"

  }
}
