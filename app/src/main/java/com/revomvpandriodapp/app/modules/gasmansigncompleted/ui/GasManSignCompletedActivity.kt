package com.revomvpandriodapp.app.modules.gasmansigncompleted.ui

import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityGasManSignCompletedBinding
import com.revomvpandriodapp.app.modules.gasmansigncompleted.`data`.viewmodel.GasManSignCompletedVM
import kotlin.String
import kotlin.Unit

class GasManSignCompletedActivity :
    BaseActivity<ActivityGasManSignCompletedBinding>(R.layout.activity_gas_man_sign_completed) {
  private val viewModel: GasManSignCompletedVM by viewModels<GasManSignCompletedVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.gasManSignCompletedVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "GAS_MAN_SIGN_COMPLETED_ACTIVITY"

  }
}
