package com.revomvpandriodapp.app.modules.gasmancontiner.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class GasmancontinerModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtHello: String? = MyApp.getInstance().resources.getString(R.string.lbl_hello)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtRevogasTextFor: String? = MyApp.getInstance().resources.getString(R.string.lbl_revogas2)
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
  var txtRetailerTextFo: String? = MyApp.getInstance().resources.getString(R.string.lbl_retailer)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSeriaIdTextFor: String? = MyApp.getInstance().resources.getString(R.string.lbl_ra_300000)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtN: String? = MyApp.getInstance().resources.getString(R.string.lbl_n)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtZero: String? = MyApp.getInstance().resources.getString(R.string.lbl_0_0)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtZeroOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_02)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtKg: String? = MyApp.getInstance().resources.getString(R.string.lbl_kg2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtQuestionsabout: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_start_selling)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtQuestionsaboutOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_wallet)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtShowall: String? = MyApp.getInstance().resources.getString(R.string.lbl_show_all)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_recent_orders)

)
