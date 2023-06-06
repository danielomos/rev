package com.revomvpandriodapp.app.modules.arealisting.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class AreaListingModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtAvailableArea: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_available_area)

)
