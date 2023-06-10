package com.revomvpandriodapp.app.modules.confirmorderdetail.ui

import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityConfirmorderdetailBinding
import com.revomvpandriodapp.app.modules.confirmorderdetail.`data`.viewmodel.ConfirmorderdetailVM
import kotlin.String
import kotlin.Unit

class ConfirmorderdetailActivity :
    BaseActivity<ActivityConfirmorderdetailBinding>(R.layout.activity_confirmorderdetail) {
  private val viewModel: ConfirmorderdetailVM by viewModels<ConfirmorderdetailVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.confirmorderdetailVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "CONFIRMORDERDETAIL_ACTIVITY"

  }
}
