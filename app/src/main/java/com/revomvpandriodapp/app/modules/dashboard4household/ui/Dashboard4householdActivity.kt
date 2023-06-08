package com.revomvpandriodapp.app.modules.dashboard4household.ui

import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityDashboard4householdBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.dashboard4household.`data`.viewmodel.Dashboard4householdVM
import com.revomvpandriodapp.app.network.models.fetchdetails1.FetchDetails1Response
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class Dashboard4householdActivity :
    BaseActivity<ActivityDashboard4householdBinding>(R.layout.activity_dashboard4household) {
  private val viewModel: Dashboard4householdVM by viewModels<Dashboard4householdVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.dashboard4householdVM = viewModel
    this@Dashboard4householdActivity.hideKeyboard()
    viewModel.callFetchDetails1Api()
  }

  override fun setUpClicks(): Unit {
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@Dashboard4householdActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@Dashboard4householdActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchDetails1LiveData.observe(this@Dashboard4householdActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchDetails1(it)
      } else if(it is ErrorResponse)  {
        onErrorFetchDetails1(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessFetchDetails1(response: SuccessResponse<FetchDetails1Response>) {
    viewModel.bindFetchDetails1Response(response.data)
  }

  private fun onErrorFetchDetails1(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_loading_profile_de)
        this@Dashboard4householdActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "DASHBOARD4HOUSEHOLD_ACTIVITY"

  }
}
