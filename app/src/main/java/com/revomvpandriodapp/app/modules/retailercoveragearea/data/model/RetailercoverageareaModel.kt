package com.revomvpandriodapp.app.modules.retailercoveragearea.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class RetailercoverageareaModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtSelectBusiness: String? =
      MyApp.getInstance().resources.getString(R.string.msg_select_business)

)
