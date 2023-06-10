package com.revomvpandriodapp.app.modules.locaton.ui

import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityLocatonBinding
import com.revomvpandriodapp.app.modules.locaton.`data`.viewmodel.LocatonVM
import kotlin.String
import kotlin.Unit

class LocatonActivity : BaseActivity<ActivityLocatonBinding>(R.layout.activity_locaton) {
  private val viewModel: LocatonVM by viewModels<LocatonVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.locatonVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "LOCATON_ACTIVITY"

  }
}
