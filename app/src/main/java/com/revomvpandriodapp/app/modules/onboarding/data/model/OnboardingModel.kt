package com.revomvpandriodapp.app.modules.onboarding.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class OnboardingModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtSkip: String? = MyApp.getInstance().resources.getString(R.string.lbl_skip)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFindGasSuppli: String? =
      MyApp.getInstance().resources.getString(R.string.msg_find_gas_suppli)

)
