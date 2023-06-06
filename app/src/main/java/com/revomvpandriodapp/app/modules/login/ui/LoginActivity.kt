package com.revomvpandriodapp.app.modules.login.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityLoginBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.isValidEmail
import com.revomvpandriodapp.app.extensions.isValidPassword
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.dashboardblank.ui.DashboardBlankActivity
import com.revomvpandriodapp.app.modules.login.`data`.viewmodel.LoginVM
import com.revomvpandriodapp.app.modules.signupaccountoptin.ui.SignupAccountOptinActivity
import com.revomvpandriodapp.app.network.models.createtoken.CreateTokenResponse
import com.revomvpandriodapp.app.network.models.fetchtype.FetchTypeResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
  private val viewModel: LoginVM by viewModels<LoginVM>()

  private val REQUEST_CODE_DASHBOARD_BLANK_ACTIVITY: Int = 628


  private val REQUEST_CODE_SIGNUP_ACCOUNT_OPTIN_ACTIVITY: Int = 479


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.loginVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnContinue.setOnClickListener {
      if(
      viewModel.loginModel.value?.etEmailphoneValue.
      isValidEmail(true)
      && viewModel.loginModel.value?.etPasswordValue.
      isValidPassword(true)) {
        this@LoginActivity.hideKeyboard()
        viewModel.callCreateTokenApi()
      }
    }
    binding.txtDonthaveacco.setOnClickListener {
      val destIntent = SignupAccountOptinActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_SIGNUP_ACCOUNT_OPTIN_ACTIVITY)
    }
  }

  override fun addObservers(): Unit {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@LoginActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@LoginActivity.showProgressDialog()
      } else {
        progressDialog?.dismiss()
      }
    }
    viewModel.createTokenLiveData.observe(this@LoginActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreateToken(it)
      } else if(it is ErrorResponse) {
        onErrorCreateToken(it.data ?:Exception())
      }
    }
    viewModel.fetchTypeLiveData.observe(this@LoginActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchType(it)
      } else if(it is ErrorResponse) {
        onErrorFetchType(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessCreateToken(response: SuccessResponse<CreateTokenResponse>): Unit {
    viewModel.bindCreateTokenResponse(response.data)
    this@LoginActivity.hideKeyboard()
    viewModel.callFetchTypeApi()
  }

  private fun onErrorCreateToken(exception: Exception): Unit {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
        else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_wrong_login_details)
        this@LoginActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  private fun onSuccessFetchType(response: SuccessResponse<FetchTypeResponse>): Unit {
    viewModel.bindFetchTypeResponse(response.data)
    val destIntent = DashboardBlankActivity.getIntent(this, null)
    startActivityForResult(destIntent, REQUEST_CODE_DASHBOARD_BLANK_ACTIVITY)
  }

  private fun onErrorFetchType(exception: Exception): Unit {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
        else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_wrong_login_detail)
        this@LoginActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "LOGIN_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, LoginActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
