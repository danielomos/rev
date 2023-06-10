package com.revomvpandriodapp.app.modules.dashboard4gasmantwo.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class Dashboard4gasmantwoModel(
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
  var txtRetailerShop: String? = MyApp.getInstance().resources.getString(R.string.lbl_retailer_shop)
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
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.msg_transfer_fund_t)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtShop: String? = MyApp.getInstance().resources.getString(R.string.lbl_shop)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTopupStockor: String? =
      MyApp.getInstance().resources.getString(R.string.msg_top_up_stock_or)
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
