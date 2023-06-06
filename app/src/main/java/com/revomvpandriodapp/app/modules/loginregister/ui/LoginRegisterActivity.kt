package com.revomvpandriodapp.app.modules.loginregister.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityLoginRegisterBinding
import com.revomvpandriodapp.app.modules.login.ui.LoginActivity
import com.revomvpandriodapp.app.modules.loginregister.`data`.viewmodel.LoginRegisterVM
import com.revomvpandriodapp.app.modules.signupaccountoptin.ui.SignupAccountOptinActivity
import kotlin.String
import kotlin.Unit

class LoginRegisterActivity :
    BaseActivity<ActivityLoginRegisterBinding>(R.layout.activity_login_register) {
  private val viewModel: LoginRegisterVM by viewModels<LoginRegisterVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.loginRegisterVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnLogin.setOnClickListener {
      val destIntent = LoginActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
    binding.btnRegister.setOnClickListener {
      val destIntent = SignupAccountOptinActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "LOGIN_REGISTER_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, LoginRegisterActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
