package com.revomvpandriodapp.app.modules.homesignupwithnumber.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityHomeSignUpWithNumberBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.isText
import com.revomvpandriodapp.app.extensions.isValidPassword
import com.revomvpandriodapp.app.extensions.isValidPhone
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.homemobiletoken.ui.HomeMobiletokenActivity
import com.revomvpandriodapp.app.modules.homesignupwithnumber.`data`.viewmodel.HomeSignUpWithNumberVM
import com.revomvpandriodapp.app.network.models.createphonesignup1.CreatePhoneSignup1Response
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class HomeSignUpWithNumberActivity :
    BaseActivity<ActivityHomeSignUpWithNumberBinding>(R.layout.activity_home_sign_up_with_number) {
  private val viewModel: HomeSignUpWithNumberVM by viewModels<HomeSignUpWithNumberVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.homeSignUpWithNumberVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnContinue.setOnClickListener {
      if(
      viewModel.homeSignUpWithNumberModel.value?.etLanguageValue.
      isValidPhone(false)
      && viewModel.homeSignUpWithNumberModel.value?.etPasswordValue.
      isValidPassword(true)
      && viewModel.homeSignUpWithNumberModel.value?.etFirstnameValue.
      isText(false)
      && viewModel.homeSignUpWithNumberModel.value?.etLastnameValue.
      isText(false)) {
        this@HomeSignUpWithNumberActivity.hideKeyboard()
        viewModel.callCreatePhoneSignup1Api()
      }
    }
  }

  override fun addObservers(): Unit {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@HomeSignUpWithNumberActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@HomeSignUpWithNumberActivity.showProgressDialog()
      } else {
        progressDialog?.dismiss()
      }
    }
    viewModel.createPhoneSignup1LiveData.observe(this@HomeSignUpWithNumberActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreatePhoneSignup1(it)
      } else if(it is ErrorResponse) {
        onErrorCreatePhoneSignup1(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessCreatePhoneSignup1(response: SuccessResponse<CreatePhoneSignup1Response>):
      Unit {
    viewModel.bindCreatePhoneSignup1Response(response.data)
    val destIntent = HomeMobiletokenActivity.getIntent(this, null)
    startActivity(destIntent)
  }

  private fun onErrorCreatePhoneSignup1(exception: Exception): Unit {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
        else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_signing_up)
        this@HomeSignUpWithNumberActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "HOME_SIGN_UP_WITH_NUMBER_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, HomeSignUpWithNumberActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
