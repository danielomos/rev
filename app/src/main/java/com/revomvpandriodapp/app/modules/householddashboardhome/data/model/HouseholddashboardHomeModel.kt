package com.revomvpandriodapp.app.modules.householddashboardhome.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class HouseholddashboardHomeModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtHouseHoldTextF: String? = MyApp.getInstance().resources.getString(R.string.lbl_hello)
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
  var txtLanguageOne: String? =
      MyApp.getInstance().resources.getString(R.string.msg_self_manage_ser)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtBA1200000TextF: String? = MyApp.getInstance().resources.getString(R.string.lbl_ba1200000)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtZero: String? = MyApp.getInstance().resources.getString(R.string.lbl_0)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtN: String? = MyApp.getInstance().resources.getString(R.string.lbl_n)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtZeroOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_0_0)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtQuestionsabout: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_refill_request)
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
  var txtRecentGasRefi: String? =
      MyApp.getInstance().resources.getString(R.string.msg_recent_gas_refi)

)
