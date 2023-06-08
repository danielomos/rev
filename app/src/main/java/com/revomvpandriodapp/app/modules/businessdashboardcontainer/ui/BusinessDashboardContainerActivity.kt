package com.revomvpandriodapp.app.modules.businessdashboardcontainer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityBusinessDashboardContainerBinding
import com.revomvpandriodapp.app.extensions.loadFragment
import com.revomvpandriodapp.app.modules.businessdashboard.ui.BusinessDashboardFragment
import com.revomvpandriodapp.app.modules.businessdashboardcontainer.`data`.viewmodel.BusinessDashboardContainerVM
import kotlin.String
import kotlin.Unit

class BusinessDashboardContainerActivity :
    BaseActivity<ActivityBusinessDashboardContainerBinding>(R.layout.activity_business_dashboard_container)
    {
  private val viewModel: BusinessDashboardContainerVM by viewModels<BusinessDashboardContainerVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.businessDashboardContainerVM = viewModel
    val destFragment = BusinessDashboardFragment.getInstance(null)
    this.loadFragment(
        R.id.fragmentContainer,
        destFragment,
        bundle = destFragment.arguments, 
        tag = BusinessDashboardFragment.TAG, 
        addToBackStack = false, 
        add = false, 
        enter = null, 
        exit = null, 
        )
  }

  override fun setUpClicks(): Unit {
    binding.linearColumnhome.setOnClickListener {
      val destFragment = BusinessDashboardFragment.getInstance(null)
      this.loadFragment(
          R.id.fragmentContainer,
          destFragment,
          bundle = destFragment.arguments, 
          tag = BusinessDashboardFragment.TAG, 
          addToBackStack = true, 
          add = false, 
          enter = null, 
          exit = null, 
          )
    }
  }

  companion object {
    const val TAG: String = "BUSINESS_DASHBOARD_CONTAINER_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, BusinessDashboardContainerActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
