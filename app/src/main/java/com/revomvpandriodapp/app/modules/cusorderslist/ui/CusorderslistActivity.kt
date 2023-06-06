package com.revomvpandriodapp.app.modules.cusorderslist.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityCusorderslistBinding
import com.revomvpandriodapp.app.modules.cusorderslist.`data`.model.Listiconfortynine1RowModel
import com.revomvpandriodapp.app.modules.cusorderslist.`data`.viewmodel.CusorderslistVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

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
    const val TAG: String = "CUSORDERSLIST_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CusorderslistActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
