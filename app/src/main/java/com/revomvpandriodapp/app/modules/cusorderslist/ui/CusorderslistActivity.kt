package com.revomvpandriodapp.app.modules.cusorderslist.ui

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
import com.revomvpandriodapp.app.databinding.ActivityCusorderslistBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.cusorderpage.ui.CusorderpageActivity
import com.revomvpandriodapp.app.modules.cusorderslist.`data`.model.Listiconfortynine1RowModel
import com.revomvpandriodapp.app.modules.cusorderslist.`data`.viewmodel.CusorderslistVM
import com.revomvpandriodapp.app.network.models.fetchall1.FetchAll1Response
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class CusorderslistActivity :
    BaseActivity<ActivityCusorderslistBinding>(R.layout.activity_cusorderslist) {
  private val viewModel: CusorderslistVM by viewModels<CusorderslistVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listiconfortynineAdapter =
    ListiconfortynineAdapter(viewModel.listiconfortynineList.value?:mutableListOf())
    binding.recyclerListiconfortynine.adapter = listiconfortynineAdapter
    listiconfortynineAdapter.setOnItemClickListener(
    object : ListiconfortynineAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Listiconfortynine1RowModel) {
        onClickRecyclerListiconfortynine(view, position, item)
      }
    }
    )
    viewModel.listiconfortynineList.observe(this) {
      listiconfortynineAdapter.updateData(it)
    }
    binding.cusorderslistVM = viewModel
    this@CusorderslistActivity.hideKeyboard()
    viewModel.callFetchAll1Api()
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerListiconfortynine(
    view: View,
    position: Int,
    item: Listiconfortynine1RowModel
  ): Unit {
    when(view.id) {
      R.id.btnDetails ->  {
        val destIntent = CusorderpageActivity.getIntent(this, null)
        startActivity(destIntent)
      }
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@CusorderslistActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@CusorderslistActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchAll1LiveData.observe(this@CusorderslistActivity) {
      if(it is SuccessResponse) {
        val response = it.getContentIfNotHandled()
        onSuccessFetchAll1(it)
      } else if(it is ErrorResponse)  {
        onErrorFetchAll1(it.data ?:Exception())
      }
    }
  }

  private fun onSuccessFetchAll1(response: SuccessResponse<FetchAll1Response>) {
    viewModel.bindFetchAll1Response(response.data)
  }

  private fun onErrorFetchAll1(exception: Exception) {
    when(exception) {
      is NoInternetConnection -> {
        Snackbar.make(binding.root, exception.message?:"", Snackbar.LENGTH_LONG).show()
      }
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
            else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_error_loading_order_hi)
        this@CusorderslistActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "CUSORDERSLIST_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CusorderslistActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
