package com.revomvpandriodapp.app.modules.loginregister.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityLoginregisterBinding
import com.revomvpandriodapp.app.modules.login.ui.LoginActivity
import com.revomvpandriodapp.app.modules.loginregister.`data`.viewmodel.LoginregisterVM
import com.revomvpandriodapp.app.modules.signupaccountoptin.ui.SignupAccountOptinActivity
import kotlin.String
import kotlin.Unit

class LoginregisterActivity :
    BaseActivity<ActivityLoginregisterBinding>(R.layout.activity_loginregister) {
  private val viewModel: LoginregisterVM by viewModels<LoginregisterVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.loginregisterVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnLogin.setOnClickListener {
      val destIntent = LoginActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnRegister.setOnClickListener {
      val destIntent = SignupAccountOptinActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "LOGINREGISTER_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, LoginregisterActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
