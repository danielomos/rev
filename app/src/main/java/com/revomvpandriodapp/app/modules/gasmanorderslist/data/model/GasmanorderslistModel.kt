package com.revomvpandriodapp.app.modules.gasmanorderslist.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class GasmanorderslistModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtOrdersHistory: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_orders_history)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNew: String? = MyApp.getInstance().resources.getString(R.string.lbl_new)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrame394: String? = MyApp.getInstance().resources.getString(R.string.lbl_31)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtProcessing: String? = MyApp.getInstance().resources.getString(R.string.lbl_processing)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrame394One: String? = MyApp.getInstance().resources.getString(R.string.lbl_11)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCompleted: String? = MyApp.getInstance().resources.getString(R.string.lbl_completed)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrame394Two: String? = MyApp.getInstance().resources.getString(R.string.lbl_8)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCanceled: String? = MyApp.getInstance().resources.getString(R.string.lbl_canceled)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrame394Three: String? = MyApp.getInstance().resources.getString(R.string.lbl_6)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtHomeOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_home)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtExpenses: String? = MyApp.getInstance().resources.getString(R.string.lbl_orders)
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
