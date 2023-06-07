package com.revomvpandriodapp.app.modules.thankyou.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class ThankYouModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTitle: String? = MyApp.getInstance().resources.getString(R.string.lbl_thank_you)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtThepagearelo: String? =
      MyApp.getInstance().resources.getString(R.string.msg_your_order_with)

)
