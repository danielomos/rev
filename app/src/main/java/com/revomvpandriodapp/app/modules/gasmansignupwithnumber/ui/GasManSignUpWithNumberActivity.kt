package com.revomvpandriodapp.app.modules.gasmansignupwithnumber.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityGasManSignUpWithNumberBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.isText
import com.revomvpandriodapp.app.extensions.isValidPassword
import com.revomvpandriodapp.app.extensions.isValidPhone
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.gasmanmobiletoken.ui.GasManMobiletokenActivity
import com.revomvpandriodapp.app.modules.gasmansignupwithnumber.`data`.viewmodel.GasManSignUpWithNumberVM
import com.revomvpandriodapp.app.network.models.createphonesignup.CreatePhoneSignupResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class GasManSignUpWithNumberActivity :
    BaseActivity<ActivityGasManSignUpWithNumberBinding>(R.layout.activity_gas_man_sign_up_with_number)
    {
  private val viewModel: GasManSignUpWithNumberVM by viewModels<GasManSignUpWithNumberVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.gasManSignUpWithNumberVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnContinue.setOnClickListener {
      if(
          viewModel.gasManSignUpWithNumberModel.value?.etLanguageValue.
      isValidPhone(false)
          && viewModel.gasManSignUpWithNumberModel.value?.etPasswordValue.
      isValidPassword(true)
          && viewModel.gasManSignUpWithNumberModel.value?.etLastnameValue.
      isText(false)
          && viewModel.gasManSignUpWithNumberModel.value?.etFirstnameValue.
      isText(false)
          && viewModel.gasManSignUpWithNumberModel.value?.etShopnameValue.
      isText(false)) {
        this@GasManSignUpWithNumberActivity.hideKeyboard()
        viewModel.callCreatePhoneSignupApi()
      }
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@GasManSignUpWithNumberActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@GasManSignUpWithNumberActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.createPhoneSignupLiveData.observe(this@GasManSignUpWithNumberActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreatePhoneSignup(it)
      } else if(it is ErrorResponse)  {
        onErrorCreatePhoneSignup(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessCreatePhoneSignup(response: SuccessResponse<CreatePhoneSignupResponse>) {
    viewModel.bindCreatePhoneSignupResponse(response.data)
    val destIntent = GasManMobiletokenActivity.getIntent(this, null)
    startActivity(destIntent)
  }

  private fun onErrorCreatePhoneSignup(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_signing_up)
        this@GasManSignUpWithNumberActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "GAS_MAN_SIGN_UP_WITH_NUMBER_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, GasManSignUpWithNumberActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
