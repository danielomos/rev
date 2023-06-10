package com.revomvpandriodapp.app.modules.confirmorderdetail.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.confirmorderdetail.`data`.model.ConfirmorderdetailModel
import org.koin.core.KoinComponent

class ConfirmorderdetailVM : ViewModel(), KoinComponent {
  val confirmorderdetailModel: MutableLiveData<ConfirmorderdetailModel> =
      MutableLiveData(ConfirmorderdetailModel())

  var navArguments: Bundle? = null
}
