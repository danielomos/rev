package com.revomvpandriodapp.app.modules.gasmanorderslist.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityGasmanorderslistBinding
import com.revomvpandriodapp.app.modules.gasmanorderslist.`data`.model.GasmanmainordrRowModel
import com.revomvpandriodapp.app.modules.gasmanorderslist.`data`.viewmodel.GasmanorderslistVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class GasmanorderslistActivity :
    BaseActivity<ActivityGasmanorderslistBinding>(R.layout.activity_gasmanorderslist) {
  private val viewModel: GasmanorderslistVM by viewModels<GasmanorderslistVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val gasmanmainordrAdapter =
    GasmanmainordrAdapter(viewModel.gasmanmainordrList.value?:mutableListOf())
    binding.recyclerGasmanmainordr.adapter = gasmanmainordrAdapter
    gasmanmainordrAdapter.setOnItemClickListener(
    object : GasmanmainordrAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : GasmanmainordrRowModel) {
        onClickRecyclerGasmanmainordr(view, position, item)
      }
    }
    )
    viewModel.gasmanmainordrList.observe(this) {
      gasmanmainordrAdapter.updateData(it)
    }
    binding.gasmanorderslistVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerGasmanmainordr(
    view: View,
    position: Int,
    item: GasmanmainordrRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "GASMANORDERSLIST_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, GasmanorderslistActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
