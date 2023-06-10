package com.revomvpandriodapp.app.modules.gasmanmobiletoken.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityGasManMobiletokenBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.gasmanmobiletoken.`data`.viewmodel.GasManMobiletokenVM
import com.revomvpandriodapp.app.modules.homesuccess.ui.HomeSuccessActivity
import com.revomvpandriodapp.app.network.models.createmobiletokenverification.CreateMobileTokenVerificationResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class GasManMobiletokenActivity :
    BaseActivity<ActivityGasManMobiletokenBinding>(R.layout.activity_gas_man_mobiletoken) {
  private val viewModel: GasManMobiletokenVM by viewModels<GasManMobiletokenVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.gasManMobiletokenVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnVerifyMobileNumber.setOnClickListener {
      this@GasManMobiletokenActivity.hideKeyboard()
      viewModel.callCreateMobileTokenVerificationApi()
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  override fun addObservers(): Unit {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@GasManMobiletokenActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@GasManMobiletokenActivity.showProgressDialog()
      } else {
        progressDialog?.dismiss()
      }
    }
    viewModel.createMobileTokenVerificationLiveData.observe(this@GasManMobiletokenActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreateMobileTokenVerification(it)
      } else if(it is ErrorResponse) {
        onErrorCreateMobileTokenVerification(it.data ?:Exception())
      }
    }
  }

  private
      fun onSuccessCreateMobileTokenVerification(response: SuccessResponse<CreateMobileTokenVerificationResponse>):
      Unit {
    viewModel.bindCreateMobileTokenVerificationResponse(response.data)
    val destIntent = HomeSuccessActivity.getIntent(this, null)
    destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(destIntent)
  }

  private fun onErrorCreateMobileTokenVerification(exception: Exception): Unit {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
        else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_wrong_code_enter)
        this@GasManMobiletokenActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "GAS_MAN_MOBILETOKEN_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, GasManMobiletokenActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
