package com.revomvpandriodapp.app.modules.homedashb.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class Gasmanorder1RowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtWeight: String? = MyApp.getInstance().resources.getString(R.string.lbl_12kg2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPendingTextFor: String? = MyApp.getInstance().resources.getString(R.string.lbl_pending)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrame247: String? = MyApp.getInstance().resources.getString(R.string.lbl_2023)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt12KgTextFormFi: String? = MyApp.getInstance().resources.getString(R.string.lbl_n)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt12KgTextFormFiOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_0_0)

)
