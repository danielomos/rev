package com.revomvpandriodapp.app.modules.orderlist4household.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityOrderlist4householdBinding
import com.revomvpandriodapp.app.modules.orderlist4household.`data`.model.Listiconfortynine1RowModel
import com.revomvpandriodapp.app.modules.orderlist4household.`data`.viewmodel.Orderlist4householdVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class Orderlist4householdActivity :
    BaseActivity<ActivityOrderlist4householdBinding>(R.layout.activity_orderlist4household) {
  private val viewModel: Orderlist4householdVM by viewModels<Orderlist4householdVM>()

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
