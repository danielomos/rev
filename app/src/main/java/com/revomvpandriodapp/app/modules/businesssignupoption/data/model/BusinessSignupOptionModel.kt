package com.revomvpandriodapp.app.modules.businesssignupoption.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class BusinessSignupOptionModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtSignUpWith: String? = MyApp.getInstance().resources.getString(R.string.lbl_sign_up_with)

)
