package com.revomvpandriodapp.app.modules.businesssignupwithnumber.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class BusinessSignUpWithNumberModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtResturantSign: String? =
      MyApp.getInstance().resources.getString(R.string.msg_resturant_sign)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etFirstnameValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etFrameThirtyEightValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etBusinessnameValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etLanguageValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etBusinessaddressValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etPasswordValue: String? = null
)
