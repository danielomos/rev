package com.revomvpandriodapp.app.modules.dashboardblank.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.constants.userType
import com.revomvpandriodapp.app.databinding.ActivityDashboardBlankBinding
import com.revomvpandriodapp.app.extensions.alert
import com.revomvpandriodapp.app.extensions.neutralButton
import com.revomvpandriodapp.app.modules.businessdashboardontainercontainer.ui.BusinessDashboardontainerContainerActivity
import com.revomvpandriodapp.app.modules.dashboard4gasmantwo.ui.Dashboard4gasmantwoActivity
import com.revomvpandriodapp.app.modules.dashboardblank.`data`.viewmodel.DashboardBlankVM
import com.revomvpandriodapp.app.modules.homedashb.ui.HomedashbActivity
import kotlin.String
import kotlin.Unit
import org.koin.android.ext.android.inject

class DashboardBlankActivity :
    BaseActivity<ActivityDashboardBlankBinding>(R.layout.activity_dashboard_blank) {
  private val viewModel: DashboardBlankVM by viewModels<DashboardBlankVM>()

  private val prefs: PreferenceHelper by inject()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.dashboardBlankVM = viewModel
    if(
    prefs.getUserType() == userType.HOUSEHOLD
    ) {
      onCreateCondition()
    }
    else if(
    prefs.getUserType() == userType.RETAILER
    ) {
      onCreateCondition1()
    }
    else if(
    prefs.getUserType() == userType.BUSINESS
    ) {
      onCreateCondition2()
    }
    else {
      onCreateCondition3()
    }
  }

  override fun setUpClicks(): Unit {
  }

  private fun onCreateCondition(): Unit {
    val destIntent = HomedashbActivity.getIntent(this, null)
    startActivity(destIntent)
    finish()
  }

  private fun onCreateCondition1(): Unit {
    val destIntent = Dashboard4gasmantwoActivity.getIntent(this, null)
    startActivity(destIntent)
    finish()
  }

  private fun onCreateCondition2(): Unit {
    val destIntent = BusinessDashboardontainerContainerActivity.getIntent(this, null)
    startActivity(destIntent)
    finish()
  }

  private fun onCreateCondition3(): Unit {
    this@DashboardBlankActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),
    MyApp.getInstance().getString(R.string.msg_invalid_user_type)) {
      neutralButton {
      }
    }
  }

  companion object {
    const val TAG: String = "DASHBOARD_BLANK_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, DashboardBlankActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
