package com.revomvpandriodapp.app.modules.gasmansignupoption.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class GasManSignupOptionModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtSignUpWith: String? = MyApp.getInstance().resources.getString(R.string.lbl_sign_up_with)

)
