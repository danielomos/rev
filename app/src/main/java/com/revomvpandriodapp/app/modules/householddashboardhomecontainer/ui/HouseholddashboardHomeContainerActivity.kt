package com.revomvpandriodapp.app.modules.householddashboardhomecontainer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityHouseholddashboardHomeContainerBinding
import com.revomvpandriodapp.app.extensions.loadFragment
import com.revomvpandriodapp.app.modules.cusorderslist.ui.CusorderslistActivity
import com.revomvpandriodapp.app.modules.householddashboardhome.ui.HouseholddashboardHomeFragment
import com.revomvpandriodapp.app.modules.householddashboardhomecontainer.`data`.viewmodel.HouseholddashboardHomeContainerVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class HouseholddashboardHomeContainerActivity :
    BaseActivity<ActivityHouseholddashboardHomeContainerBinding>(R.layout.activity_householddashboard_home_container)
    {
  private val viewModel: HouseholddashboardHomeContainerVM by
      viewModels<HouseholddashboardHomeContainerVM>()

  private val REQUEST_CODE_CUSORDERSLIST_ACTIVITY: Int = 776


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.householddashboardHomeContainerVM = viewModel
    val destFragment = HouseholddashboardHomeFragment.getInstance(null)
    this.loadFragment(
    R.id.fragmentContainer,
    destFragment,
    bundle = destFragment.arguments,
    tag = HouseholddashboardHomeFragment.TAG,
    addToBackStack = false,
    add = false,
    enter = null,
    exit = null,
    )
  }

  override fun setUpClicks(): Unit {
    binding.linearColumnvolume.setOnClickListener {
      val destIntent = CusorderslistActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CUSORDERSLIST_ACTIVITY)
    }
    binding.linearColumnhome.setOnClickListener {
      val destFragment = HouseholddashboardHomeFragment.getInstance(null)
      this.loadFragment(
      R.id.fragmentContainer,
      destFragment,
      bundle = destFragment.arguments,
      tag = HouseholddashboardHomeFragment.TAG,
      addToBackStack = true,
      add = false,
      enter = null,
      exit = null,
      )
    }
  }

  companion object {
    const val TAG: String = "HOUSEHOLDDASHBOARD_HOME_CONTAINER_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, HouseholddashboardHomeContainerActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
