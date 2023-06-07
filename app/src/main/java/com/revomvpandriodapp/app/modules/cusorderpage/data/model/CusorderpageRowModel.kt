package com.revomvpandriodapp.app.modules.cusorderpage.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class CusorderpageRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtOrderCode: String? = MyApp.getInstance().resources.getString(R.string.lbl_order_code)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOrderAmount: String? = MyApp.getInstance().resources.getString(R.string.lbl_order_amount)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOrderQuantity: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_order_quantity)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPickUpKg: String? = MyApp.getInstance().resources.getString(R.string.lbl_pickup_kg)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDeliveryKg: String? = MyApp.getInstance().resources.getString(R.string.lbl_delivery_kg)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOrderedDate: String? = MyApp.getInstance().resources.getString(R.string.lbl_ordered_date)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtRetailerBusinesOne: String? =
      MyApp.getInstance().resources.getString(R.string.msg_retailerbusines)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtRetailerName: String? = MyApp.getInstance().resources.getString(R.string.lbl_retailer_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtRetailerPhone: String? =
      MyApp.getInstance().resources.getString(R.string.msg_retailer_phone)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOrderStatus: String? = MyApp.getInstance().resources.getString(R.string.lbl_order_status)

)
