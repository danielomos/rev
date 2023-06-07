package com.revomvpandriodapp.app.modules.homesuccess.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityHomeSuccessBinding
import com.revomvpandriodapp.app.modules.homesuccess.`data`.viewmodel.HomeSuccessVM
import com.revomvpandriodapp.app.modules.login.ui.LoginActivity
import kotlin.String
import kotlin.Unit

class HomeSuccessActivity : BaseActivity<ActivityHomeSuccessBinding>(R.layout.activity_home_success)
    {
  private val viewModel: HomeSuccessVM by viewModels<HomeSuccessVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.homeSuccessVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnContinue.setOnClickListener {
      val destIntent = LoginActivity.getIntent(this, null)
      startActivity(destIntent)
      finish()
    }
  }

  companion object {
    const val TAG: String = "HOME_SUCCESS_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, HomeSuccessActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
