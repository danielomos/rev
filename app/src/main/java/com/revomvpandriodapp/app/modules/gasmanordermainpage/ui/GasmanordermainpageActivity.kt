package com.revomvpandriodapp.app.modules.gasmanordermainpage.ui

import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityGasmanordermainpageBinding
import com.revomvpandriodapp.app.modules.gasmanordermainpage.`data`.viewmodel.GasmanordermainpageVM
import kotlin.String
import kotlin.Unit

class GasmanordermainpageActivity :
    BaseActivity<ActivityGasmanordermainpageBinding>(R.layout.activity_gasmanordermainpage) {
  private val viewModel: GasmanordermainpageVM by viewModels<GasmanordermainpageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.gasmanordermainpageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "GASMANORDERMAINPAGE_ACTIVITY"

  }
}
