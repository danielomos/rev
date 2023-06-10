package com.revomvpandriodapp.app.modules.businesssignupwithnumber.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityBusinessSignUpWithNumberBinding
import com.revomvpandriodapp.app.modules.businesssignupwithnumber.`data`.viewmodel.BusinessSignUpWithNumberVM
import kotlin.String
import kotlin.Unit

class BusinessSignUpWithNumberActivity :
    BaseActivity<ActivityBusinessSignUpWithNumberBinding>(R.layout.activity_business_sign_up_with_number)
    {
  private val viewModel: BusinessSignUpWithNumberVM by viewModels<BusinessSignUpWithNumberVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.businessSignUpWithNumberVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "BUSINESS_SIGN_UP_WITH_NUMBER_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, BusinessSignUpWithNumberActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
