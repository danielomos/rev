package com.revomvpandriodapp.app.modules.businesssignupoption.ui

import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityBusinessSignupOptionBinding
import com.revomvpandriodapp.app.modules.businesssignupoption.`data`.viewmodel.BusinessSignupOptionVM
import com.revomvpandriodapp.app.modules.businesssignupwitemail.ui.BusinessSignUpWitEmailActivity
import com.revomvpandriodapp.app.modules.businesssignupwithnumber.ui.BusinessSignUpWithNumberActivity
import kotlin.String
import kotlin.Unit

class BusinessSignupOptionActivity :
    BaseActivity<ActivityBusinessSignupOptionBinding>(R.layout.activity_business_signup_option) {
  private val viewModel: BusinessSignupOptionVM by viewModels<BusinessSignupOptionVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.businessSignupOptionVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnAnEmail.setOnClickListener {
      val destIntent = BusinessSignUpWitEmailActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnAMobileNumberOne.setOnClickListener {
      val destIntent = BusinessSignUpWithNumberActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "BUSINESS_SIGNUP_OPTION_ACTIVITY"

  }
}
