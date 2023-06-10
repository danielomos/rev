package com.revomvpandriodapp.app.modules.retailersetup.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityRetailerSetupBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.retailersetup.`data`.viewmodel.RetailerSetupVM
import com.revomvpandriodapp.app.network.models.createsetup.CreateSetupResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class RetailerSetupActivity :
    BaseActivity<ActivityRetailerSetupBinding>(R.layout.activity_retailer_setup) {
  private val viewModel: RetailerSetupVM by viewModels<RetailerSetupVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.retailerSetupVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnContinue.setOnClickListener {
      this@RetailerSetupActivity.hideKeyboard()
      viewModel.callCreateSetupApi()
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@RetailerSetupActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@RetailerSetupActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.createSetupLiveData.observe(this@RetailerSetupActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreateSetup(it)
      } else if(it is ErrorResponse)  {
        onErrorCreateSetup(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessCreateSetup(response: SuccessResponse<CreateSetupResponse>) {
    this@RetailerSetupActivity.alert(MyApp.getInstance().getString(R.string.lbl_successful),
    MyApp.getInstance().getString(R.string.msg_profile_setup_successful)) {
      neutralButton {
      }
    }
    viewModel.bindCreateSetupResponse(response.data)
  }

  private fun onErrorCreateSetup(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_setting_up_pr)
        this@RetailerSetupActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "RETAILER_SETUP_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, RetailerSetupActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
