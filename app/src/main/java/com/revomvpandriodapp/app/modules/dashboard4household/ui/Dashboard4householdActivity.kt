package com.revomvpandriodapp.app.modules.dashboard4household.ui

import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityDashboard4householdBinding
import com.revomvpandriodapp.app.modules.dashboard4household.`data`.viewmodel.Dashboard4householdVM
import kotlin.String
import kotlin.Unit

class Dashboard4householdActivity :
    BaseActivity<ActivityDashboard4householdBinding>(R.layout.activity_dashboard4household) {
  private val viewModel: Dashboard4householdVM by viewModels<Dashboard4householdVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.dashboard4householdVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "DASHBOARD4HOUSEHOLD_ACTIVITY"

  }
}
