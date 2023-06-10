package com.revomvpandriodapp.app.modules.businessdashboardontainer.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseFragment
import com.revomvpandriodapp.app.databinding.FragmentBusinessDashboardontainerBinding
import com.revomvpandriodapp.app.modules.businessdashboardontainer.`data`.model.GasmanordersRowModel
import com.revomvpandriodapp.app.modules.businessdashboardontainer.`data`.model.ListiconfortynineRowModel
import com.revomvpandriodapp.app.modules.businessdashboardontainer.`data`.viewmodel.BusinessDashboardontainerVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class BusinessDashboardontainerFragment :
    BaseFragment<FragmentBusinessDashboardontainerBinding>(R.layout.fragment_business_dashboardontainer)
    {
  private val viewModel: BusinessDashboardontainerVM by viewModels<BusinessDashboardontainerVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
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
    viewModel.listiconfortynineList.observe(requireActivity()) {
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
    viewModel.gasmanordersList.observe(requireActivity()) {
      gasmanordersAdapter.updateData(it)
    }
    binding.businessDashboardontainerVM = viewModel
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
    const val TAG: String = "BUSINESS_DASHBOARDONTAINER_FRAGMENT"


    fun getInstance(bundle: Bundle?): BusinessDashboardontainerFragment {
      val fragment = BusinessDashboardontainerFragment()
      fragment.arguments = bundle
      return fragment
    }
  }
}
