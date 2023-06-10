package com.revomvpandriodapp.app.modules.deliveryorderdetail.ui

import androidx.activity.viewModels
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.base.BaseActivity
import com.revomvpandriodapp.app.databinding.ActivityDeliveryorderdetailBinding
import com.revomvpandriodapp.app.modules.deliveryorderdetail.`data`.viewmodel.DeliveryorderdetailVM
import kotlin.String
import kotlin.Unit

class DeliveryorderdetailActivity :
    BaseActivity<ActivityDeliveryorderdetailBinding>(R.layout.activity_deliveryorderdetail) {
  private val viewModel: DeliveryorderdetailVM by viewModels<DeliveryorderdetailVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.deliveryorderdetailVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "DELIVERYORDERDETAIL_ACTIVITY"

  }
}
