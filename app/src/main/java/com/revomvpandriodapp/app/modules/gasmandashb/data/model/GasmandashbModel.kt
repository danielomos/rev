package com.revomvpandriodapp.app.modules.gasmandashb.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class GasmandashbModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_hello)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtHouseHoldTextF: String? = MyApp.getInstance().resources.getString(R.string.lbl_revogas2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDeliverCooking: String? =
      MyApp.getInstance().resources.getString(R.string.msg_deliver_cooking)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtStockLevel: String? = MyApp.getInstance().resources.getString(R.string.lbl_stock_level)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtZero: String? = MyApp.getInstance().resources.getString(R.string.lbl_02)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSelfManageSer: String? =
      MyApp.getInstance().resources.getString(R.string.msg_self_manage_ser)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtRV100001TextF: String? = MyApp.getInstance().resources.getString(R.string.lbl_rv100001)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSellingPrice: String? = MyApp.getInstance().resources.getString(R.string.lbl_selling_price)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt1200Kg: String? = MyApp.getInstance().resources.getString(R.string.lbl_1200_kg)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtRecentGasRefi: String? =
      MyApp.getInstance().resources.getString(R.string.msg_recent_gas_refi)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtShowall: String? = MyApp.getInstance().resources.getString(R.string.lbl_show_all)
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
  var txtWallet: String? = MyApp.getInstance().resources.getString(R.string.lbl_help)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtProfile: String? = MyApp.getInstance().resources.getString(R.string.lbl_menu)

)
