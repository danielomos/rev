package com.revomvpandriodapp.app.modules.gasmanemailtoken.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class GasManEmailtokenModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtEnter6digitV: String? =
      MyApp.getInstance().resources.getString(R.string.msg_enter_6_digit_v)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.msg_code_send_to_y)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageOne: String? = MyApp.getInstance().resources.getString(R.string.msg_don_t_get_code)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etGasManEmailTValue: String? = null
)
