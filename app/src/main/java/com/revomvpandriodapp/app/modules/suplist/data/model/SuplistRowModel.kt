package com.revomvpandriodapp.app.modules.suplist.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class SuplistRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtRevoGas: String? = MyApp.getInstance().resources.getString(R.string.lbl_revo_gas)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt10YabaRoad: String? = MyApp.getInstance().resources.getString(R.string.msg_10_yaba_road)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtKg: String? = MyApp.getInstance().resources.getString(R.string.lbl_kg)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etFrame247Value: String? = null
)
