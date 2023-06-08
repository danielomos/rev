package com.revomvpandriodapp.app.modules.dashboard4gasman.ui

import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityDashboard4gasmanBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.dashboard4gasman.`data`.viewmodel.Dashboard4gasmanVM
import com.revomvpandriodapp.app.network.models.fetchdetails.FetchDetailsResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class Dashboard4gasmanActivity :
    BaseActivity<ActivityDashboard4gasmanBinding>(R.layout.activity_dashboard4gasman) {
  private val viewModel: Dashboard4gasmanVM by viewModels<Dashboard4gasmanVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.dashboard4gasmanVM = viewModel
    this@Dashboard4gasmanActivity.hideKeyboard()
    viewModel.callFetchDetailsApi()
  }

  override fun setUpClicks(): Unit {
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@Dashboard4gasmanActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@Dashboard4gasmanActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchDetailsLiveData.observe(this@Dashboard4gasmanActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchDetails(it)
      } else if(it is ErrorResponse)  {
        onErrorFetchDetails(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessFetchDetails(response: SuccessResponse<FetchDetailsResponse>) {
    viewModel.bindFetchDetailsResponse(response.data)
  }

  private fun onErrorFetchDetails(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_loading_profile_de)
        this@Dashboard4gasmanActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "DASHBOARD4GASMAN_ACTIVITY"

  }
}
