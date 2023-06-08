package com.revomvpandriodapp.app.modules.businesssignupcomplete.ui

import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityBusinessSignupCompleteBinding
import com.revomvpandriodapp.app.modules.businesssignupcomplete.`data`.viewmodel.BusinessSignupCompleteVM
import kotlin.String
import kotlin.Unit

class BusinessSignupCompleteActivity :
    BaseActivity<ActivityBusinessSignupCompleteBinding>(R.layout.activity_business_signup_complete)
    {
  private val viewModel: BusinessSignupCompleteVM by viewModels<BusinessSignupCompleteVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.businessSignupCompleteVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "BUSINESS_SIGNUP_COMPLETE_ACTIVITY"

  }
}
