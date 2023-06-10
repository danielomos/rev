package com.revomvpandriodapp.app.modules.orderlist4household.ui

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
import com.revomvpandriodapp.app.databinding.ActivityOrderlist4householdBinding
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.hideKeyboard
import com.revomvpandriodapp.app.extensions.isJSONObject
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.extensions.showProgressDialog
import com.revomvpandriodapp.app.modules.noactionorderhousehoddetail.ui.NoactionorderhousehoddetailActivity
import com.revomvpandriodapp.app.modules.orderlist4household.`data`.model.Listiconfortynine1RowModel
import com.revomvpandriodapp.app.modules.orderlist4household.`data`.viewmodel.Orderlist4householdVM
import com.revomvpandriodapp.app.network.models.fetchall1.FetchAll1Response
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class Orderlist4householdActivity :
    BaseActivity<ActivityOrderlist4householdBinding>(R.layout.activity_orderlist4household) {
  private val viewModel: Orderlist4householdVM by viewModels<Orderlist4householdVM>()

  private val REQUEST_CODE_NOACTIONORDERHOUSEHODDETAIL_ACTIVITY: Int = 757

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
    binding.orderlist4householdVM = viewModel
    this@Orderlist4householdActivity.hideKeyboard()
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
        val destBundle = Bundle()
        destBundle.putString("orderStatus",Gson().toJson(viewModel.fetchAllLiveData.value?.getSuccessResponse()?.payload?.get(position)?.orderStatus))
        destBundle.putString("cusOrderId",Gson().toJson(viewModel.fetchAllLiveData.value?.getSuccessResponse()?.payload?.get(position)?.cusOrderId))
        val destIntent = NoactionorderhousehoddetailActivity.getIntent(this, destBundle)
        startActivityForResult(destIntent, REQUEST_CODE_NOACTIONORDERHOUSEHODDETAIL_ACTIVITY)
      }
    }
  }

  override fun addObservers() {
    var progressDialog : AlertDialog? = null
    viewModel.progressLiveData.observe(this@Orderlist4householdActivity) {
      if(it) {
        progressDialog?.dismiss()
        progressDialog = null
        progressDialog = this@Orderlist4householdActivity.showProgressDialog()
      } else  {
        progressDialog?.dismiss()
      }
    }
    viewModel.fetchAll1LiveData.observe(this@Orderlist4householdActivity) {
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
        this@Orderlist4householdActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "ORDERLIST4HOUSEHOLD_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, Orderlist4householdActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
