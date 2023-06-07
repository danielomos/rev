package com.revomvpandriodapp.app.modules.onboarding.ui

import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityOnboardingBinding
import com.revomvpandriodapp.app.modules.loginregister.ui.LoginRegisterActivity
import com.revomvpandriodapp.app.modules.onboarding.`data`.viewmodel.OnboardingVM
import kotlin.String
import kotlin.Unit

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {
  private val viewModel: OnboardingVM by viewModels<OnboardingVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.onboardingVM = viewModel
    Handler(Looper.getMainLooper()).postDelayed( {
      val destIntent = LoginRegisterActivity.getIntent(this, null)
      startActivity(destIntent)
      finish()
      }, 3000)
    }

    override fun setUpClicks(): Unit {
    }

    companion object {
      const val TAG: String = "ONBOARDING_ACTIVITY"

    }
  }
