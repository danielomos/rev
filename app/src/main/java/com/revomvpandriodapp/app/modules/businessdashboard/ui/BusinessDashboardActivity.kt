package com.revomvpandriodapp.app.modules.businessdashboard.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityBusinessDashboardBinding
import com.revomvpandriodapp.app.modules.businessdashboard.`data`.model.GasmanordersRowModel
import com.revomvpandriodapp.app.modules.businessdashboard.`data`.model.ListiconfortynineRowModel
import com.revomvpandriodapp.app.modules.businessdashboard.`data`.viewmodel.BusinessDashboardVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class BusinessDashboardActivity :
    BaseActivity<ActivityBusinessDashboardBinding>(R.layout.activity_business_dashboard) {
  private val viewModel: BusinessDashboardVM by viewModels<BusinessDashboardVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listiconfortynineAdapter =
    ListiconfortynineAdapter(viewModel.listiconfortynineList.value?:mutableListOf())
    binding.recyclerListiconfortynine.adapter = listiconfortynineAdapter
    listiconfortynineAdapter.setOnItemClickListener(
    object : ListiconfortynineAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListiconfortynineRowModel) {
        onClickRecyclerListiconfortynine(view, position, item)
      }
    }
    )
    viewModel.listiconfortynineList.observe(this) {
      listiconfortynineAdapter.updateData(it)
    }
    val gasmanordersAdapter =
    GasmanordersAdapter(viewModel.gasmanordersList.value?:mutableListOf())
    binding.recyclerGasmanorders.adapter = gasmanordersAdapter
    gasmanordersAdapter.setOnItemClickListener(
    object : GasmanordersAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : GasmanordersRowModel) {
        onClickRecyclerGasmanorders(view, position, item)
      }
    }
    )
    viewModel.gasmanordersList.observe(this) {
      gasmanordersAdapter.updateData(it)
    }
    binding.businessDashboardVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerListiconfortynine(
    view: View,
    position: Int,
    item: ListiconfortynineRowModel
  ): Unit {
    when(view.id) {
    }
  }

  fun onClickRecyclerGasmanorders(
    view: View,
    position: Int,
    item: GasmanordersRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "BUSINESS_DASHBOARD_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, BusinessDashboardActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
