package com.revomvpandriodapp.app.modules.homemobiletoken.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityHomeMobiletokenBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.homemobiletoken.`data`.viewmodel.HomeMobiletokenVM
import com.revomvpandriodapp.app.modules.homesuccess.ui.HomeSuccessActivity
import com.revomvpandriodapp.app.network.models.createmobiletokenverification.CreateMobileTokenVerificationResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class HomeMobiletokenActivity :
    BaseActivity<ActivityHomeMobiletokenBinding>(R.layout.activity_home_mobiletoken) {
  private val viewModel: HomeMobiletokenVM by viewModels<HomeMobiletokenVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.homeMobiletokenVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnVerifyMobileNumber.setOnClickListener {
      this@HomeMobiletokenActivity.hideKeyboard()
      viewModel.callCreateMobileTokenVerificationApi()
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@HomeMobiletokenActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@HomeMobiletokenActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.createMobileTokenVerificationLiveData.observe(this@HomeMobiletokenActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreateMobileTokenVerification(it)
      } else if(it is ErrorResponse)  {
        onErrorCreateMobileTokenVerification(it.data ?:Exception())
      }
    }
  }

  private
      fun onSuccessCreateMobileTokenVerification(response: SuccessResponse<CreateMobileTokenVerificationResponse>) {
    viewModel.bindCreateMobileTokenVerificationResponse(response.data)
    val destIntent = HomeSuccessActivity.getIntent(this, null)
    destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(destIntent)
  }

  private fun onErrorCreateMobileTokenVerification(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_wrong_code_enter)
        this@HomeMobiletokenActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "HOME_MOBILETOKEN_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, HomeMobiletokenActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
