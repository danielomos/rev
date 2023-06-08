package com.revomvpandriodapp.app.modules.orderlist4gasman.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityOrderlist4gasmanBinding
import com.revomvpandriodapp.app.modules.orderlist4gasman.`data`.model.GasmanmainordrRowModel
import com.revomvpandriodapp.app.modules.orderlist4gasman.`data`.viewmodel.Orderlist4gasmanVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class Orderlist4gasmanActivity :
    BaseActivity<ActivityOrderlist4gasmanBinding>(R.layout.activity_orderlist4gasman) {
  private val viewModel: Orderlist4gasmanVM by viewModels<Orderlist4gasmanVM>()

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
    binding.orderlist4gasmanVM = viewModel
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
    const val TAG: String = "ORDERLIST4GASMAN_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, Orderlist4gasmanActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
