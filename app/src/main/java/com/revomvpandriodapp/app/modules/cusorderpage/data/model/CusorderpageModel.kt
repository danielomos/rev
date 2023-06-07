package com.revomvpandriodapp.app.modules.cusorderpage.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class CusorderpageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtRefillDetails: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_refill_details)

)
