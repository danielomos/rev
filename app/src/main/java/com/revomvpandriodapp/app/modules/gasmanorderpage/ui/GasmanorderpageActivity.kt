package com.revomvpandriodapp.app.modules.gasmanorderpage.ui

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
import com.revomvpandriodapp.app.databinding.ActivityGasmanorderpageBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.gasmandashboardhome.ui.GasmandashboardHomeActivity
import com.revomvpandriodapp.app.modules.gasmanorderpage.`data`.model.ListordercodeRowModel
import com.revomvpandriodapp.app.modules.gasmanorderpage.`data`.viewmodel.GasmanorderpageVM
import com.revomvpandriodapp.app.network.models.fetchid.FetchIdResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class GasmanorderpageActivity :
    BaseActivity<ActivityGasmanorderpageBinding>(R.layout.activity_gasmanorderpage) {
  private val viewModel: GasmanorderpageVM by viewModels<GasmanorderpageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listordercodeAdapter =
    ListordercodeAdapter(viewModel.listordercodeList.value?:mutableListOf())
    binding.recyclerListordercode.adapter = listordercodeAdapter
    listordercodeAdapter.setOnItemClickListener(
    object : ListordercodeAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListordercodeRowModel) {
        onClickRecyclerListordercode(view, position, item)
      }
    }
    )
    viewModel.listordercodeList.observe(this) {
      listordercodeAdapter.updateData(it)
    }
    binding.gasmanorderpageVM = viewModel
    this@GasmanorderpageActivity.hideKeyboard()
    viewModel.callFetchIdApi()
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnBackToHomepageOne.setOnClickListener {
      val destIntent = GasmandashboardHomeActivity.getIntent(this, null)
      startActivity(destIntent)
      finish()
    }
  }

  fun onClickRecyclerListordercode(
    view: View,
    position: Int,
    item: ListordercodeRowModel
  ): Unit {
    when(view.id) {
    }
  }

  override fun addObservers(): Unit {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@GasmanorderpageActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@GasmanorderpageActivity.showProgressDialog()
      } else {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchIdLiveData.observe(this@GasmanorderpageActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchId(it)
      } else if(it is ErrorResponse) {
        onErrorFetchId(it.data ?:Exception())
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
        this@GasmanorderpageActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "GASMANORDERPAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, GasmanorderpageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
