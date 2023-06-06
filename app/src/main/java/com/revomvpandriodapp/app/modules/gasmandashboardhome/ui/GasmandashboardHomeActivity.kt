package com.revomvpandriodapp.app.modules.gasmandashboardhome.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityGasmandashboardHomeBinding
import com.revomvpandriodapp.app.extensions.loadFragment
import com.revomvpandriodapp.app.modules.gasmancontiner.ui.GasmancontinerFragment
import com.revomvpandriodapp.app.modules.gasmandashboardhome.`data`.viewmodel.GasmandashboardHomeVM
import com.revomvpandriodapp.app.modules.gasmanorderslist.ui.GasmanorderslistActivity
import kotlin.String
import kotlin.Unit

class GasmandashboardHomeActivity :
    BaseActivity<ActivityGasmandashboardHomeBinding>(R.layout.activity_gasmandashboard_home) {
  private val viewModel: GasmandashboardHomeVM by viewModels<GasmandashboardHomeVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.gasmandashboardHomeVM = viewModel
    val destFragment = GasmancontinerFragment.getInstance(null)
    this.loadFragment(
        R.id.fragmentContainer,
        destFragment,
        bundle = destFragment.arguments, 
        tag = GasmancontinerFragment.TAG, 
        addToBackStack = false, 
        add = false, 
        enter = null, 
        exit = null, 
        )
  }

  override fun setUpClicks(): Unit {
    binding.linearColumnhome.setOnClickListener {
      // TODO please, add your navigation code here
    }
    binding.linearColumnvolume.setOnClickListener {
      val destIntent = GasmanorderslistActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "GASMANDASHBOARD_HOME_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, GasmandashboardHomeActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
