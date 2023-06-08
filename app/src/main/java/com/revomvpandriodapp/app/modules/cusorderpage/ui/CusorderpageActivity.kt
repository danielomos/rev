package com.revomvpandriodapp.app.modules.cusorderpage.ui

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
import com.revomvpandriodapp.app.databinding.ActivityCusorderpageBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.cusorderpage.`data`.model.CusorderpageRowModel
import com.revomvpandriodapp.app.modules.cusorderpage.`data`.viewmodel.CusorderpageVM
import com.revomvpandriodapp.app.network.models.fetchid1.FetchId1Response
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class CusorderpageActivity :
    BaseActivity<ActivityCusorderpageBinding>(R.layout.activity_cusorderpage) {
  private val viewModel: CusorderpageVM by viewModels<CusorderpageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val cusorderpageAdapter =
    CusorderpageAdapter(viewModel.cusorderpageList.value?:mutableListOf())
    binding.recyclerCusorderpage.adapter = cusorderpageAdapter
    cusorderpageAdapter.setOnItemClickListener(
    object : CusorderpageAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : CusorderpageRowModel) {
        onClickRecyclerCusorderpage(view, position, item)
      }
    }
    )
    viewModel.cusorderpageList.observe(this) {
      cusorderpageAdapter.updateData(it)
    }
    binding.cusorderpageVM = viewModel
    this@CusorderpageActivity.hideKeyboard()
    viewModel.callFetchId1Api()
  }

  override fun setUpClicks(): Unit {
    binding.btnBackToHomepageOne.setOnClickListener {
      // TODO please, add your navigation code here
      finish()
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerCusorderpage(
    view: View,
    position: Int,
    item: CusorderpageRowModel
  ): Unit {
    when(view.id) {
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@CusorderpageActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@CusorderpageActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchId1LiveData.observe(this@CusorderpageActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchId1(it)
      } else if(it is ErrorResponse)  {
        onErrorFetchId1(it.data ?:Exception())
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
        this@CusorderpageActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "CUSORDERPAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CusorderpageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
