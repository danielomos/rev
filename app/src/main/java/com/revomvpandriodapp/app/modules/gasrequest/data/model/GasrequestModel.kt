package com.revomvpandriodapp.app.modules.gasrequest.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class GasrequestModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtRetailersDeta: String? =
      MyApp.getInstance().resources.getString(R.string.msg_retailer_s_deta)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtRevoGasShop: String? = MyApp.getInstance().resources.getString(R.string.lbl_revo_gas_shop)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_seller_s_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_shop_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_selleing_price)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_shop_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDeliveryCharge: String? =
      MyApp.getInstance().resources.getString(R.string.msg_delivery_charge)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_shop_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt0000: String? = MyApp.getInstance().resources.getString(R.string.lbl_00_00)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etLastnameValue: String? = null
)
