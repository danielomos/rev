package com.revomvpandriodapp.app.modules.housedash.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class HousedashModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtHouseHoldTextF: String? = MyApp.getInstance().resources.getString(R.string.lbl_hello2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtHouseHoldTextFOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_seun)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.msg_enjoy_seamless)
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
  var txtRV100001: String? = MyApp.getInstance().resources.getString(R.string.lbl_rv100001)
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
  var txtFundWallet: String? = MyApp.getInstance().resources.getString(R.string.lbl_fund_wallet)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTransferFundT: String? =
      MyApp.getInstance().resources.getString(R.string.msg_transfer_fund_t)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtGasRefill: String? = MyApp.getInstance().resources.getString(R.string.lbl_gas_refill)
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
  var txtShowall: String? = MyApp.getInstance().resources.getString(R.string.lbl_show_all)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_recent_orders)

)
