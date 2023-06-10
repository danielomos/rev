package com.revomvpandriodapp.app.modules.businessdashboardontainer.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class GasmanordersRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtRevoGasTextFoOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_revo_gas)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPendingTextForOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_pending)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt10042023TextOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_10_04_2023)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtWeightOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_12kg)

)
