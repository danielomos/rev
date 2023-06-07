package com.revomvpandriodapp.app.modules.suplist.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class SuplistModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_retailers)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNearYou: String? = MyApp.getInstance().resources.getString(R.string.lbl_near_you)

)
