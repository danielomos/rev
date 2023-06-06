package com.revomvpandriodapp.app.modules.businessemailtoken.ui

import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityBusinessEmailtokenBinding
import com.revomvpandriodapp.app.modules.businessemailtoken.`data`.viewmodel.BusinessEmailtokenVM
import kotlin.String
import kotlin.Unit

class BusinessEmailtokenActivity :
    BaseActivity<ActivityBusinessEmailtokenBinding>(R.layout.activity_business_emailtoken) {
  private val viewModel: BusinessEmailtokenVM by viewModels<BusinessEmailtokenVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.businessEmailtokenVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "BUSINESS_EMAILTOKEN_ACTIVITY"

  }
}
