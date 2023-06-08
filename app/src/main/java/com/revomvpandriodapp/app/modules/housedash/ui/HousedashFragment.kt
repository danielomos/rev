package com.revomvpandriodapp.app.modules.housedash.ui

import android.view.View
import androidx.fragment.app.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseFragment
import com.revomvpandriodapp.app.databinding.FragmentHousedashBinding
import com.revomvpandriodapp.app.modules.housedash.`data`.model.Gasmanorder2RowModel
import com.revomvpandriodapp.app.modules.housedash.`data`.viewmodel.HousedashVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class HousedashFragment : BaseFragment<FragmentHousedashBinding>(R.layout.fragment_housedash) {
  private val viewModel: HousedashVM by viewModels<HousedashVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    val gasmanorderAdapter =
    GasmanorderAdapter(viewModel.gasmanorderList.value?:mutableListOf())
    binding.recyclerGasmanorder.adapter = gasmanorderAdapter
    gasmanorderAdapter.setOnItemClickListener(
    object : GasmanorderAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Gasmanorder2RowModel) {
        onClickRecyclerGasmanorder(view, position, item)
      }
    }
    )
    viewModel.gasmanorderList.observe(requireActivity()) {
      gasmanorderAdapter.updateData(it)
    }
    binding.housedashVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerGasmanorder(
    view: View,
    position: Int,
    item: Gasmanorder2RowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "HOUSEDASH_FRAGMENT"

  }
}
