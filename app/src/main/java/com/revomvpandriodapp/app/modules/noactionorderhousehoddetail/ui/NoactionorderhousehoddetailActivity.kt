package com.revomvpandriodapp.app.modules.noactionorderhousehoddetail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.constants.CustomerOrderStatus
import com.revomvpandriodapp.app.databinding.ActivityNoactionorderhousehoddetailBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.homedashb.ui.HomedashbActivity
import com.revomvpandriodapp.app.modules.noactionorderhousehoddetail.`data`.viewmodel.NoactionorderhousehoddetailVM
import com.revomvpandriodapp.app.network.models.createcompleted.CreateCompletedResponse
import com.revomvpandriodapp.app.network.models.fetchid1.FetchId1Response
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class NoactionorderhousehoddetailActivity :
    BaseActivity<ActivityNoactionorderhousehoddetailBinding>(R.layout.activity_noactionorderhousehoddetail)
    {
  private val viewModel: NoactionorderhousehoddetailVM by
      viewModels<NoactionorderhousehoddetailVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.noactionorderhousehoddetailVM = viewModel
    this@NoactionorderhousehoddetailActivity.hideKeyboard()
    viewModel.callFetchId1Api()
  }

  override fun setUpClicks(): Unit {
    binding.linearColumndescription.setOnClickListener {
      if(
      viewModel.fetchId_LiveData.value?.getSuccessResponse()?.payload?.orderStatus ==
          CustomerOrderStatus.DELIVERED
      ) {
        binding.linearColumndescription.visibility = View.VISIBLE
        linearColumndescriptionCondition()
      }
      else {
        binding.linearColumndescription.visibility = View.GONE
        linearColumndescriptionCondition1()
      }
    }
    binding.btnBackToHomepageOne.setOnClickListener {
      val destIntent = HomedashbActivity.getIntent(this, null)
      destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnConfirmRefillOne.setOnClickListener {
      this@NoactionorderhousehoddetailActivity.hideKeyboard()
      viewModel.callCreateCompletedApi()
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@NoactionorderhousehoddetailActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@NoactionorderhousehoddetailActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchId1LiveData.observe(this@NoactionorderhousehoddetailActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchId1(it)
      } else if(it is ErrorResponse)  {
        onErrorFetchId1(it.data ?:Exception())
      }
    }
    viewModel.createCompletedLiveData.observe(this@NoactionorderhousehoddetailActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreateCompleted(it)
      } else if(it is ErrorResponse)  {
        onErrorCreateCompleted(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessFetchId1(response: SuccessResponse<FetchId1Response>) {
    viewModel.bindFetchId1Response(response.data)
  }

  private fun onErrorFetchId1(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_loading_order_de)
        this@NoactionorderhousehoddetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  private fun linearColumndescriptionCondition() {
  }

  private fun linearColumndescriptionCondition1() {
  }

  private fun onSuccessCreateCompleted(response: SuccessResponse<CreateCompletedResponse>) {
    this@NoactionorderhousehoddetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_successful),
    MyApp.getInstance().getString(R.string.lbl_confirm)) {
      neutralButton {
      }
    }
    viewModel.bindCreateCompletedResponse(response.data)
  }

  private fun onErrorCreateCompleted(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage =
            MyApp.getInstance().getString(R.string.msg_error_confirming_refill_order_st)
        this@NoactionorderhousehoddetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "NOACTIONORDERHOUSEHODDETAIL_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, NoactionorderhousehoddetailActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
