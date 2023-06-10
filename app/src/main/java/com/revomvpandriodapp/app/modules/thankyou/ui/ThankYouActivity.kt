package com.revomvpandriodapp.app.modules.thankyou.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityThankYouBinding
import com.revomvpandriodapp.app.modules.homedashb.ui.HomedashbActivity
import com.revomvpandriodapp.app.modules.thankyou.`data`.viewmodel.ThankYouVM
import kotlin.String
import kotlin.Unit

class ThankYouActivity : BaseActivity<ActivityThankYouBinding>(R.layout.activity_thank_you) {
  private val viewModel: ThankYouVM by viewModels<ThankYouVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.thankYouVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnBackToHomepageOne.setOnClickListener {
      val destIntent = HomedashbActivity.getIntent(this, null)
      destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "THANK_YOU_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ThankYouActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
