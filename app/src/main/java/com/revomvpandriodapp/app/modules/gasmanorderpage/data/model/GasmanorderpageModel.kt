package com.revomvpandriodapp.app.modules.gasmanorderpage.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class GasmanorderpageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtRefillDetails: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_refill_details)

)
