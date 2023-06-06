package com.revomvpandriodapp.app.modules.suplist.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class SuplistModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtRetailersin: String? = MyApp.getInstance().resources.getString(R.string.lbl_retailers_in)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtIkorodu: String? = MyApp.getInstance().resources.getString(R.string.lbl_ikorodu)

)
