package com.revomvpandriodapp.app.modules.businesssignupwitemail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityBusinessSignUpWitEmailBinding
import com.revomvpandriodapp.app.modules.businesssignupwitemail.`data`.viewmodel.BusinessSignUpWitEmailVM
import kotlin.String
import kotlin.Unit

class BusinessSignUpWitEmailActivity :
    BaseActivity<ActivityBusinessSignUpWitEmailBinding>(R.layout.activity_business_sign_up_wit_email)
    {
  private val viewModel: BusinessSignUpWitEmailVM by viewModels<BusinessSignUpWitEmailVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.businessSignUpWitEmailVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "BUSINESS_SIGN_UP_WIT_EMAIL_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, BusinessSignUpWitEmailActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
