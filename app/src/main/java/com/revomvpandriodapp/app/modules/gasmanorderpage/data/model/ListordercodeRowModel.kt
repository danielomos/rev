package com.revomvpandriodapp.app.modules.gasmanorderpage.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class ListordercodeRowModel(
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
  var txtRefillQuantity: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_refill_quantity)
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
  var txtOrderStatus: String? = MyApp.getInstance().resources.getString(R.string.lbl_order_status)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCustomerAddres: String? =
      MyApp.getInstance().resources.getString(R.string.msg_customer_addres)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOrderDate: String? = MyApp.getInstance().resources.getString(R.string.lbl_order_date)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCustomerName: String? = MyApp.getInstance().resources.getString(R.string.lbl_customer_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCustomerPhone: String? =
      MyApp.getInstance().resources.getString(R.string.msg_customer_phone)

)
