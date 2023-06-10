package com.revomvpandriodapp.app.modules.businessdashboardontainercontainer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityBusinessDashboardontainerContainerBinding
import com.revomvpandriodapp.app.extensions.loadFragment
import com.revomvpandriodapp.app.modules.businessdashboardontainer.ui.BusinessDashboardontainerFragment
import com.revomvpandriodapp.app.modules.businessdashboardontainercontainer.`data`.viewmodel.BusinessDashboardontainerContainerVM
import kotlin.String
import kotlin.Unit

class BusinessDashboardontainerContainerActivity :
    BaseActivity<ActivityBusinessDashboardontainerContainerBinding>(R.layout.activity_business_dashboardontainer_container)
    {
  private val viewModel: BusinessDashboardontainerContainerVM by
      viewModels<BusinessDashboardontainerContainerVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.businessDashboardontainerContainerVM = viewModel
    val destFragment = BusinessDashboardontainerFragment.getInstance(null)
    this.loadFragment(
        R.id.fragmentContainer,
        destFragment,
        bundle = destFragment.arguments, 
        tag = BusinessDashboardontainerFragment.TAG, 
        addToBackStack = false, 
        add = false, 
        enter = null, 
        exit = null, 
        )
  }

  override fun setUpClicks(): Unit {
    binding.linearColumnhome.setOnClickListener {
      val destFragment = BusinessDashboardontainerFragment.getInstance(null)
      this.loadFragment(
          R.id.fragmentContainer,
          destFragment,
          bundle = destFragment.arguments, 
          tag = BusinessDashboardontainerFragment.TAG, 
          addToBackStack = true, 
          add = false, 
          enter = null, 
          exit = null, 
          )
    }
  }

  companion object {
    const val TAG: String = "BUSINESS_DASHBOARDONTAINER_CONTAINER_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, BusinessDashboardontainerContainerActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
