package com.revomvpandriodapp.app.modules.gasmandashb.`data`.model

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.String

data class ListcalculatorRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtFundWallet: String? = MyApp.getInstance().resources.getString(R.string.lbl_fund_wallet)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTransferFundT: String? =
      MyApp.getInstance().resources.getString(R.string.msg_transfer_fund_t)

)
