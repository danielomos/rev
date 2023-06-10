package com.revomvpandriodapp.app.modules.businessdashboardontainercontainer.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class BusinessDashboardontainerContainerModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtHomeOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_home)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtExpenses: String? = MyApp.getInstance().resources.getString(R.string.lbl_supplier)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtWallet: String? = MyApp.getInstance().resources.getString(R.string.lbl_support)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtProfile: String? = MyApp.getInstance().resources.getString(R.string.lbl_menu)

)
