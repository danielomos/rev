package com.revomvpandriodapp.app.modules.signupaccountoptin.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivitySignupAccountOptinBinding
import com.revomvpandriodapp.app.modules.gasmansignupoption.ui.GasManSignupOptionActivity
import com.revomvpandriodapp.app.modules.homesignupoption.ui.HomeSignupOptionActivity
import com.revomvpandriodapp.app.modules.signupaccountoptin.`data`.viewmodel.SignupAccountOptinVM
import kotlin.String
import kotlin.Unit

class SignupAccountOptinActivity :
    BaseActivity<ActivitySignupAccountOptinBinding>(R.layout.activity_signup_account_optin) {
  private val viewModel: SignupAccountOptinVM by viewModels<SignupAccountOptinVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.signupAccountOptinVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnRetailerProfileOne.setOnClickListener {
      val destIntent = GasManSignupOptionActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnHouseholdProfile.setOnClickListener {
      val destIntent = HomeSignupOptionActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "SIGNUP_ACCOUNT_OPTIN_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SignupAccountOptinActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
