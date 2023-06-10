package com.revomvpandriodapp.app.modules.homesignupwitemail.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class HomeSignUpWitEmailModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtHouseHoldSign: String? =
      MyApp.getInstance().resources.getString(R.string.msg_household_sign)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etFirstnameValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etLastnameValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etLanguageValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etEmailValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etPasswordValue: String? = null
)
