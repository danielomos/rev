package com.revomvpandriodapp.app.modules.homedashb.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class HomedashbModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_hello)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtHouseHoldTextF: String? = MyApp.getInstance().resources.getString(R.string.lbl_dan)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageOne: String? = MyApp.getInstance().resources.getString(R.string.msg_enjoy_seamless)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtStockLevel: String? = MyApp.getInstance().resources.getString(R.string.lbl_gas_level)
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
  var txtTodaysPrice: String? = MyApp.getInstance().resources.getString(R.string.lbl_today_s_price)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt1200Kg: String? = MyApp.getInstance().resources.getString(R.string.lbl_1200_kg)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFundWallet: String? = MyApp.getInstance().resources.getString(R.string.lbl_fund_wallet)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageTwo: String? =
      MyApp.getInstance().resources.getString(R.string.msg_transfer_fund_t)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtRefillGas: String? = MyApp.getInstance().resources.getString(R.string.lbl_refill_gas)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTopuporRefil: String? =
      MyApp.getInstance().resources.getString(R.string.msg_top_up_or_refil)
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
