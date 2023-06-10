package com.revomvpandriodapp.app.modules.businessdashboardontainer.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class ListiconfortynineRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtRevoGasTextFo: String? = MyApp.getInstance().resources.getString(R.string.lbl_revo_gas)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPendingTextFor: String? = MyApp.getInstance().resources.getString(R.string.lbl_pending)

)
