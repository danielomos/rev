package com.revomvpandriodapp.app.modules.homesignupoption.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityHomeSignupOptionBinding
import com.revomvpandriodapp.app.modules.homesignupoption.`data`.viewmodel.HomeSignupOptionVM
import com.revomvpandriodapp.app.modules.homesignupwitemail.ui.HomeSignUpWitEmailActivity
import com.revomvpandriodapp.app.modules.homesignupwithnumber.ui.HomeSignUpWithNumberActivity
import kotlin.String
import kotlin.Unit

class HomeSignupOptionActivity :
    BaseActivity<ActivityHomeSignupOptionBinding>(R.layout.activity_home_signup_option) {
  private val viewModel: HomeSignupOptionVM by viewModels<HomeSignupOptionVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.homeSignupOptionVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnAnEmail.setOnClickListener {
      val destIntent = HomeSignUpWitEmailActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnAMobileNumberOne.setOnClickListener {
      val destIntent = HomeSignUpWithNumberActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "HOME_SIGNUP_OPTION_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, HomeSignupOptionActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
