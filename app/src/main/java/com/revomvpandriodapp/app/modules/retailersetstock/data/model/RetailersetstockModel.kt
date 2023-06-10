package com.revomvpandriodapp.app.modules.retailersetstock.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class RetailersetstockModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtHowcanwehelp: String? =
      MyApp.getInstance().resources.getString(R.string.msg_set_your_stock)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAddStockQuant: String? =
      MyApp.getInstance().resources.getString(R.string.msg_add_stock_quant)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSellingPriceK: String? =
      MyApp.getInstance().resources.getString(R.string.msg_selling_price_k)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etFirstnameValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etLastnameValue: String? = null
)
