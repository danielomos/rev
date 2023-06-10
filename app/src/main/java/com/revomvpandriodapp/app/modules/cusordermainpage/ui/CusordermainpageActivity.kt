package com.revomvpandriodapp.app.modules.cusordermainpage.ui

import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityCusordermainpageBinding
import com.revomvpandriodapp.app.modules.cusordermainpage.`data`.viewmodel.CusordermainpageVM
import kotlin.String
import kotlin.Unit

class CusordermainpageActivity :
    BaseActivity<ActivityCusordermainpageBinding>(R.layout.activity_cusordermainpage) {
  private val viewModel: CusordermainpageVM by viewModels<CusordermainpageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.cusordermainpageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "CUSORDERMAINPAGE_ACTIVITY"

  }
}
