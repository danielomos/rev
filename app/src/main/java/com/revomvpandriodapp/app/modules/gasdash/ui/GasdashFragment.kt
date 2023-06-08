package com.revomvpandriodapp.app.modules.gasdash.ui

import android.view.View
import androidx.fragment.app.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseFragment
import com.revomvpandriodapp.app.databinding.FragmentGasdashBinding
import com.revomvpandriodapp.app.modules.gasdash.`data`.model.Listiconfortynine2RowModel
import com.revomvpandriodapp.app.modules.gasdash.`data`.viewmodel.GasdashVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class GasdashFragment : BaseFragment<FragmentGasdashBinding>(R.layout.fragment_gasdash) {
  private val viewModel: GasdashVM by viewModels<GasdashVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    val listiconfortynineAdapter =
    ListiconfortynineAdapter(viewModel.listiconfortynineList.value?:mutableListOf())
    binding.recyclerListiconfortynine.adapter = listiconfortynineAdapter
    listiconfortynineAdapter.setOnItemClickListener(
    object : ListiconfortynineAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Listiconfortynine2RowModel) {
        onClickRecyclerListiconfortynine(view, position, item)
      }
    }
    )
    viewModel.listiconfortynineList.observe(requireActivity()) {
      listiconfortynineAdapter.updateData(it)
    }
    binding.gasdashVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerListiconfortynine(
    view: View,
    position: Int,
    item: Listiconfortynine2RowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "GASDASH_FRAGMENT"

  }
}
