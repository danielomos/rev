package com.revomvpandriodapp.app.modules.pickorderdetail.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class PickorderdetailModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtOrdersDetails: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_order_s_details)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOrderCode: String? = MyApp.getInstance().resources.getString(R.string.lbl_order_code)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt1010101010101: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_1010101010101)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOrderAmount: String? = MyApp.getInstance().resources.getString(R.string.lbl_order_amount)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtHibroWhats: String? = MyApp.getInstance().resources.getString(R.string.msg_hi_bro_what_s)
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
  var txtWhatdoyouthi: String? =
      MyApp.getInstance().resources.getString(R.string.msg_what_do_you_thi)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCustomerName: String? = MyApp.getInstance().resources.getString(R.string.lbl_customer_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtInteresttopar: String? =
      MyApp.getInstance().resources.getString(R.string.msg_interest_to_par)
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
  var txtSendisachat: String? = MyApp.getInstance().resources.getString(R.string.msg_send_is_a_chat)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCustomersPhon: String? =
      MyApp.getInstance().resources.getString(R.string.msg_customer_s_phon)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSendisachatOne: String? =
      MyApp.getInstance().resources.getString(R.string.msg_send_is_a_chat)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOrderDate: String? = MyApp.getInstance().resources.getString(R.string.lbl_order_date)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSendisachatTwo: String? =
      MyApp.getInstance().resources.getString(R.string.msg_send_is_a_chat)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPickUpWeight: String? = MyApp.getInstance().resources.getString(R.string.lbl_pickup_weight)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSendisachatThree: String? =
      MyApp.getInstance().resources.getString(R.string.msg_send_is_a_chat)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_delivery_weight)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSendisachatFour: String? =
      MyApp.getInstance().resources.getString(R.string.msg_send_is_a_chat)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOrderStatus: String? = MyApp.getInstance().resources.getString(R.string.lbl_order_status)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSendisachatFive: String? =
      MyApp.getInstance().resources.getString(R.string.msg_send_is_a_chat)

)
