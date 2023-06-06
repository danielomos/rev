package com.revomvpandriodapp.app.modules.retailersetup.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class RetailerSetupModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtCompleteShopP: String? =
      MyApp.getInstance().resources.getString(R.string.msg_complete_shop_p)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtShopAddress: String? = MyApp.getInstance().resources.getString(R.string.lbl_shop_address)
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
  var txtDeliveryCharge: String? =
      MyApp.getInstance().resources.getString(R.string.msg_delivery_charge)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etFirstnameValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etFirstnameOneValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etLastnameValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etLanguageValue: String? = null
)
