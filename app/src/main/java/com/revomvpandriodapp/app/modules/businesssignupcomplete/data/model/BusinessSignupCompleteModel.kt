package com.revomvpandriodapp.app.modules.businesssignupcomplete.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class BusinessSignupCompleteModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtCongratsSignU: String? =
      MyApp.getInstance().resources.getString(R.string.msg_congrats_sign)

)
