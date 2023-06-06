package com.revomvpandriodapp.app.modules.gasrequest.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivityGasrequestBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.gasrequest.`data`.viewmodel.GasrequestVM
import com.revomvpandriodapp.app.modules.thankyou.ui.ThankYouActivity
import com.revomvpandriodapp.app.network.models.createrefill.CreateRefillResponse
import com.revomvpandriodapp.app.network.models.createtotal.CreateTotalResponse
import com.revomvpandriodapp.app.network.models.fetchdetail.FetchDetailResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class GasrequestActivity : BaseActivity<ActivityGasrequestBinding>(R.layout.activity_gasrequest) {
  private val viewModel: GasrequestVM by viewModels<GasrequestVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.gasrequestVM = viewModel
    this@GasrequestActivity.hideKeyboard()
    viewModel.callFetchDetailApi()
  }

  override fun setUpClicks(): Unit {
    binding.btnCalculateTotalOne.setOnClickListener {
      this@GasrequestActivity.hideKeyboard()
      viewModel.callCreateTotalApi()
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@GasrequestActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@GasrequestActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.createRefillLiveData.observe(this@GasrequestActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreateRefill(it)
      } else if(it is ErrorResponse)  {
        onErrorCreateRefill(it.data ?:Exception())
      }
    }
    viewModel.fetchDetailLiveData.observe(this@GasrequestActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchDetail(it)
      } else if(it is ErrorResponse)  {
        onErrorFetchDetail(it.data ?:Exception())
      }
    }
    viewModel.createTotalLiveData.observe(this@GasrequestActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreateTotal(it)
      } else if(it is ErrorResponse)  {
        onErrorCreateTotal(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessCreateRefill(response: SuccessResponse<CreateRefillResponse>) {
    viewModel.bindCreateRefillResponse(response.data)
    val destIntent = ThankYouActivity.getIntent(this, null)
    startActivity(destIntent)
    finish()
  }

  private fun onErrorCreateRefill(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_placing_your_gas_re_re)
        this@GasrequestActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  private fun onSuccessFetchDetail(response: SuccessResponse<FetchDetailResponse>) {
    viewModel.bindFetchDetailResponse(response.data)
  }

  private fun onErrorFetchDetail(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
    }
  }

  private fun onSuccessCreateTotal(response: SuccessResponse<CreateTotalResponse>) {
    viewModel.bindCreateTotalResponse(response.data)
  }

  private fun onErrorCreateTotal(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
    }
  }

  companion object {
    const val TAG: String = "GASREQUEST_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, GasrequestActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
