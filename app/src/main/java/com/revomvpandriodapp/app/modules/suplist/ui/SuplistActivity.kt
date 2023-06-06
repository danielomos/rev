package com.revomvpandriodapp.app.modules.suplist.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.databinding.ActivitySuplistBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.gasrequest.ui.GasrequestActivity
import com.revomvpandriodapp.app.modules.suplist.`data`.model.SuplistRowModel
import com.revomvpandriodapp.app.modules.suplist.`data`.viewmodel.SuplistVM
import com.revomvpandriodapp.app.network.models.fetchdistributors.FetchDistributorsResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class SuplistActivity : BaseActivity<ActivitySuplistBinding>(R.layout.activity_suplist) {
  private val viewModel: SuplistVM by viewModels<SuplistVM>()

  private val REQUEST_CODE_GASREQUEST_ACTIVITY: Int = 562

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val suplistAdapter = SuplistAdapter(viewModel.suplistList.value?:mutableListOf())
    binding.recyclerSuplist.adapter = suplistAdapter
    suplistAdapter.setOnItemClickListener(
    object : SuplistAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : SuplistRowModel) {
        onClickRecyclerSuplist(view, position, item)
      }
    }
    )
    viewModel.suplistList.observe(this) {
      suplistAdapter.updateData(it)
    }
    binding.suplistVM = viewModel
    this@SuplistActivity.hideKeyboard()
    viewModel.callFetchDistributorsApi()
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerSuplist(
    view: View,
    position: Int,
    item: SuplistRowModel
  ): Unit {
    when(view.id) {
      R.id.btnShopsDetails ->  {
        val destBundle = Bundle()
        destBundle.putString("sellerId",Gson().toJson(viewModel.fetchDistributorsLiveData.value?.getSuccessResponse()?.payload?.distributions?.get(position)?.sellerId))
        val destIntent = GasrequestActivity.getIntent(this, destBundle)
        startActivityForResult(destIntent, REQUEST_CODE_GASREQUEST_ACTIVITY)
      }
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@SuplistActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@SuplistActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchDistributorsLiveData.observe(this@SuplistActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchDistributors(it)
      } else if(it is ErrorResponse)  {
        onErrorFetchDistributors(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessFetchDistributors(response: SuccessResponse<FetchDistributorsResponse>) {
    viewModel.bindFetchDistributorsResponse(response.data)
  }

  private fun onErrorFetchDistributors(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage =
            MyApp.getInstance().getString(R.string.msg_error_loading_retailer_av_in_this_area)
        this@SuplistActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "SUPLIST_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SuplistActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
