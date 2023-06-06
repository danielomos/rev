package com.revomvpandriodapp.app.modules.homesignupwitemail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityHomeSignUpWitEmailBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.isText
import com.revomvpandriodapp.app.extensions.isValidEmail
import com.revomvpandriodapp.app.extensions.isValidPassword
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.homeemailtoken.ui.HomeEmailtokenActivity
import com.revomvpandriodapp.app.modules.homesignupwitemail.`data`.viewmodel.HomeSignUpWitEmailVM
import com.revomvpandriodapp.app.network.models.createemailsignup1.CreateEmailSignup1Response
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class HomeSignUpWitEmailActivity :
    BaseActivity<ActivityHomeSignUpWitEmailBinding>(R.layout.activity_home_sign_up_wit_email) {
  private val viewModel: HomeSignUpWitEmailVM by viewModels<HomeSignUpWitEmailVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.homeSignUpWitEmailVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnContinue.setOnClickListener {
      if(
          viewModel.homeSignUpWitEmailModel.value?.etLanguageValue.
      isValidEmail(true)
          && viewModel.homeSignUpWitEmailModel.value?.etPasswordValue.
      isValidPassword(true)
          && viewModel.homeSignUpWitEmailModel.value?.etFirstnameValue.
      isText(false)
          && viewModel.homeSignUpWitEmailModel.value?.etLastnameValue.
      isText(false)) {
        this@HomeSignUpWitEmailActivity.hideKeyboard()
        viewModel.callCreateEmailSignup1Api()
      }
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@HomeSignUpWitEmailActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@HomeSignUpWitEmailActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.createEmailSignup1LiveData.observe(this@HomeSignUpWitEmailActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreateEmailSignup1(it)
      } else if(it is ErrorResponse)  {
        onErrorCreateEmailSignup1(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessCreateEmailSignup1(response: SuccessResponse<CreateEmailSignup1Response>) {
    viewModel.bindCreateEmailSignup1Response(response.data)
    val destIntent = HomeEmailtokenActivity.getIntent(this, null)
    startActivity(destIntent)
  }

  private fun onErrorCreateEmailSignup1(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_signing_up)
        this@HomeSignUpWitEmailActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "HOME_SIGN_UP_WIT_EMAIL_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, HomeSignUpWitEmailActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
