package com.revomvpandriodapp.app.modules.gasmansignupwithemail.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class GasManSignUpWithEmailModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtGasManSignUp: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_gas_man_sign_up)
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
  var etShopnameValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etShopnameOneValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etLanguageValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etPasssordValue: String? = null
)
