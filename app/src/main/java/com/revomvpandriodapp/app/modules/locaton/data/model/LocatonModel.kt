package com.revomvpandriodapp.app.modules.locaton.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class LocatonModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtHomeAddress: String? = MyApp.getInstance().resources.getString(R.string.lbl_home_address)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etEnterAddressEdValue: String? = null
)
