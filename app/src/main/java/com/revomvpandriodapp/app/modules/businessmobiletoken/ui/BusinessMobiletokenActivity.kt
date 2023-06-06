package com.revomvpandriodapp.app.modules.businessmobiletoken.ui

import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityBusinessMobiletokenBinding
import com.revomvpandriodapp.app.modules.businessmobiletoken.`data`.viewmodel.BusinessMobiletokenVM
import kotlin.String
import kotlin.Unit

class BusinessMobiletokenActivity :
    BaseActivity<ActivityBusinessMobiletokenBinding>(R.layout.activity_business_mobiletoken) {
  private val viewModel: BusinessMobiletokenVM by viewModels<BusinessMobiletokenVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.businessMobiletokenVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "BUSINESS_MOBILETOKEN_ACTIVITY"

  }
}
