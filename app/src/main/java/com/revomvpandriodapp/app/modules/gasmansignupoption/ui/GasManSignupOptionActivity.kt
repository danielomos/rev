package com.revomvpandriodapp.app.modules.gasmansignupoption.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityGasManSignupOptionBinding
import com.revomvpandriodapp.app.modules.gasmansignupoption.`data`.viewmodel.GasManSignupOptionVM
import com.revomvpandriodapp.app.modules.gasmansignupwithemail.ui.GasManSignUpWithEmailActivity
import com.revomvpandriodapp.app.modules.gasmansignupwithnumber.ui.GasManSignUpWithNumberActivity
import kotlin.String
import kotlin.Unit

class GasManSignupOptionActivity :
    BaseActivity<ActivityGasManSignupOptionBinding>(R.layout.activity_gas_man_signup_option) {
  private val viewModel: GasManSignupOptionVM by viewModels<GasManSignupOptionVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.gasManSignupOptionVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnAnEmail.setOnClickListener {
      val destIntent = GasManSignUpWithEmailActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnAMobileNumberOne.setOnClickListener {
      val destIntent = GasManSignUpWithNumberActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "GAS_MAN_SIGNUP_OPTION_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, GasManSignupOptionActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
