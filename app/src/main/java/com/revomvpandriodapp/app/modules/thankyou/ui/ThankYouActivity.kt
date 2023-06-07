package com.revomvpandriodapp.app.modules.thankyou.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityThankYouBinding
import com.revomvpandriodapp.app.modules.householddashboardhomecontainer.ui.HouseholddashboardHomeContainerActivity
import com.revomvpandriodapp.app.modules.thankyou.`data`.viewmodel.ThankYouVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ThankYouActivity : BaseActivity<ActivityThankYouBinding>(R.layout.activity_thank_you) {
  private val viewModel: ThankYouVM by viewModels<ThankYouVM>()

  private val REQUEST_CODE_HOUSEHOLDDASHBOARD_HOME_CONTAINER_ACTIVITY: Int = 350


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.thankYouVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnBackToHomepageOne.setOnClickListener {
      val destIntent = HouseholddashboardHomeContainerActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_HOUSEHOLDDASHBOARD_HOME_CONTAINER_ACTIVITY)
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
