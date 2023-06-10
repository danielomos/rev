package com.revomvpandriodapp.app.modules.noactionordergasmandetail.ui

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
import com.revomvpandriodapp.app.constants.GasManOrderStatus
import com.revomvpandriodapp.app.databinding.ActivityNoactionordergasmandetailBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.dashboard4gasmantwo.ui.Dashboard4gasmantwoActivity
import com.revomvpandriodapp.app.modules.noactionordergasmandetail.`data`.viewmodel.NoactionordergasmandetailVM
import com.revomvpandriodapp.app.network.models.createaccept.CreateAcceptResponse
import com.revomvpandriodapp.app.network.models.createdelivered.CreateDeliveredResponse
import com.revomvpandriodapp.app.network.models.createpickup.CreatePickupResponse
import com.revomvpandriodapp.app.network.models.fetchid.FetchIdResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class NoactionordergasmandetailActivity :
    BaseActivity<ActivityNoactionordergasmandetailBinding>(R.layout.activity_noactionordergasmandetail)
    {
  private val viewModel: NoactionordergasmandetailVM by viewModels<NoactionordergasmandetailVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.noactionordergasmandetailVM = viewModel
    this@NoactionordergasmandetailActivity.hideKeyboard()
    viewModel.callFetchIdApi()
  }

  override fun setUpClicks(): Unit {
    binding.linearRowframethirtyeight.setOnClickListener {
      if(
      viewModel.fetchId_LiveData.value?.getSuccessResponse()?.payload?.orderStatus ==
      GasManOrderStatus.PENDINGPICKUP
      ) {
        binding.linearRowframethirtyeight.visibility = View.VISIBLE
        linearRowframethirtyeightCondition()
      }
      else {
        binding.linearRowframethirtyeight.visibility = View.GONE
        linearRowframethirtyeightCondition1()
      }
    }
    binding.btnEnterPickKg.setOnClickListener {
      this@NoactionordergasmandetailActivity.hideKeyboard()
      viewModel.callCreatePickupApi()
    }
    binding.linearColumnmarkRefillDelivered.setOnClickListener {
      if(
      viewModel.fetchId_LiveData.value?.getSuccessResponse()?.payload?.orderStatus ==
      GasManOrderStatus.PENDINGDELIVERY
      ) {
        binding.linearColumnmarkRefillDelivered.visibility = View.VISIBLE
        linearColumnmarkRefillDeliveredCondition()
      }
      else {
        binding.linearColumnmarkRefillDelivered.visibility = View.GONE
        linearColumnmarkRefillDeliveredCondition1()
      }
    }
    binding.linearColumndescription.setOnClickListener {
      if(
      viewModel.fetchId_LiveData.value?.getSuccessResponse()?.payload?.orderStatus ==
      GasManOrderStatus.NEWORDER
      ) {
        binding.linearColumndescription.visibility = View.VISIBLE
        linearColumndescriptionCondition()
      }
      else {
        binding.linearColumndescription.visibility = View.GONE
        linearColumndescriptionCondition1()
      }
    }
    binding.btnMarkRefillDelivered.setOnClickListener {
      this@NoactionordergasmandetailActivity.hideKeyboard()
      viewModel.callCreateDeliveredApi()
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnBackToHomepageOne.setOnClickListener {
      val destIntent = Dashboard4gasmantwoActivity.getIntent(this, null)
      destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
      startActivity(destIntent)
    }
    binding.btnAccept.setOnClickListener {
      this@NoactionordergasmandetailActivity.hideKeyboard()
      viewModel.callCreateAcceptApi()
    }
    binding.txtDescriptionOne.setOnClickListener {
      if(
      viewModel.fetchId_LiveData.value?.getSuccessResponse()?.payload?.orderStatus ==
      GasManOrderStatus.PENDINGPICKUP
      ) {
        binding.txtDescriptionOne.visibility = View.VISIBLE
        txtDescriptionOneCondition()
      }
      else {
        binding.txtDescriptionOne.visibility = View.GONE
        txtDescriptionOneCondition1()
      }
    }
  }

  override fun addObservers(): Unit {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@NoactionordergasmandetailActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@NoactionordergasmandetailActivity.showProgressDialog()
      } else {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchIdLiveData.observe(this@NoactionordergasmandetailActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchId(it)
      } else if(it is ErrorResponse) {
        onErrorFetchId(it.data ?:Exception())
      }
    }
    viewModel.createPickupLiveData.observe(this@NoactionordergasmandetailActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreatePickup(it)
      } else if(it is ErrorResponse) {
        onErrorCreatePickup(it.data ?:Exception())
      }
    }
    viewModel.createDeliveredLiveData.observe(this@NoactionordergasmandetailActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreateDelivered(it)
      } else if(it is ErrorResponse) {
        onErrorCreateDelivered(it.data ?:Exception())
      }
    }
    viewModel.createAcceptLiveData.observe(this@NoactionordergasmandetailActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessCreateAccept(it)
      } else if(it is ErrorResponse) {
        onErrorCreateAccept(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessFetchId(response: SuccessResponse<FetchIdResponse>): Unit {
    viewModel.bindFetchIdResponse(response.data)
  }

  private fun onErrorFetchId(exception: Exception): Unit {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
        else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_loading_order_de)
        this@NoactionordergasmandetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  private fun linearRowframethirtyeightCondition(): Unit {
  }

  private fun linearRowframethirtyeightCondition1(): Unit {
  }

  private fun onSuccessCreatePickup(response: SuccessResponse<CreatePickupResponse>): Unit {
    this@NoactionordergasmandetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_successful),
    MyApp.getInstance().getString(R.string.msg_pickup_order_status_set)) {
      neutralButton {
      }
    }
    viewModel.bindCreatePickupResponse(response.data)
  }

  private fun onErrorCreatePickup(exception: Exception): Unit {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
        else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_setting_order_st)
        this@NoactionordergasmandetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  private fun linearColumnmarkRefillDeliveredCondition(): Unit {
  }

  private fun linearColumnmarkRefillDeliveredCondition1(): Unit {
  }

  private fun linearColumndescriptionCondition(): Unit {
  }

  private fun linearColumndescriptionCondition1(): Unit {
  }

  private fun onSuccessCreateDelivered(response: SuccessResponse<CreateDeliveredResponse>): Unit {
    this@NoactionordergasmandetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_successful),
    MyApp.getInstance().getString(R.string.msg_order_delivering_status_set)) {
      neutralButton {
      }
    }
    viewModel.bindCreateDeliveredResponse(response.data)
  }

  private fun onErrorCreateDelivered(exception: Exception): Unit {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
        else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_setting_order_de_st)
        this@NoactionordergasmandetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  private fun onSuccessCreateAccept(response: SuccessResponse<CreateAcceptResponse>): Unit {
    this@NoactionordergasmandetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_successful),
    MyApp.getInstance().getString(R.string.msg_order_status_set)) {
      neutralButton {
      }
    }
    viewModel.bindCreateAcceptResponse(response.data)
  }

  private fun onErrorCreateAccept(exception: Exception): Unit {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
        else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_setting_order_st)
        this@NoactionordergasmandetailActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  private fun txtDescriptionOneCondition(): Unit {
  }

  private fun txtDescriptionOneCondition1(): Unit {
  }

  companion object {
    const val TAG: String = "NOACTIONORDERGASMANDETAIL_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, NoactionordergasmandetailActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
