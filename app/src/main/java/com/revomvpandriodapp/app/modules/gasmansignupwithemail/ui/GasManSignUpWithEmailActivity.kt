package com.revomvpandriodapp.app.modules.gasmansignupwithemail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityGasManSignUpWithEmailBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.isText
import com.revomvpandriodapp.app.extensions.isValidEmail
import com.revomvpandriodapp.app.extensions.isValidPassword
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.gasmanemailtoken.ui.GasManEmailtokenActivity
import com.revomvpandriodapp.app.modules.gasmansignupwithemail.`data`.viewmodel.GasManSignUpWithEmailVM
import com.revomvpandriodapp.app.network.models.createemailsignup.CreateEmailSignupResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class GasManSignUpWithEmailActivity :
    BaseActivity<ActivityGasManSignUpWithEmailBinding>(R.layout.activity_gas_man_sign_up_with_email)
    {
  private val viewModel: GasManSignUpWithEmailVM by viewModels<GasManSignUpWithEmailVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.gasManSignUpWithEmailVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnContinue.setOnClickListener {
      if(
          viewModel.gasManSignUpWithEmailModel.value?.etLanguageValue.
      isValidEmail(true)
          && viewModel.gasManSignUpWithEmailModel.value?.etPasssordValue.
      isValidPassword(true)
          && viewModel.gasManSignUpWithEmailModel.value?.etFirstnameValue.
      isText(false)
          && viewModel.gasManSignUpWithEmailModel.value?.etLastnameValue.
      isText(false)
          && viewModel.gasManSignUpWithEmailModel.value?.etShopnameValue.
      isText(false)) {
        this@GasManSignUpWithEmailActivity.hideKeyboard()
        viewModel.callCreateEmailSignupApi()
      }
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@GasManSignUpWithEmailActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@GasManSignUpWithEmailActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.createEmailSignupLiveData.observe(this@GasManSignUpWithEmailActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreateEmailSignup(it)
      } else if(it is ErrorResponse)  {
        onErrorCreateEmailSignup(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessCreateEmailSignup(response: SuccessResponse<CreateEmailSignupResponse>) {
    viewModel.bindCreateEmailSignupResponse(response.data)
    val destIntent = GasManEmailtokenActivity.getIntent(this, null)
    startActivity(destIntent)
  }

  private fun onErrorCreateEmailSignup(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_signing_up)
        this@GasManSignUpWithEmailActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "GAS_MAN_SIGN_UP_WITH_EMAIL_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, GasManSignUpWithEmailActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
