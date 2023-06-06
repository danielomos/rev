package com.revomvpandriodapp.app.modules.gasmandashboardhome.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.gasmandashboardhome.`data`.model.GasmandashboardHomeModel
import org.koin.core.KoinComponent

class GasmandashboardHomeVM : ViewModel(), KoinComponent {
  val gasmandashboardHomeModel: MutableLiveData<GasmandashboardHomeModel> =
      MutableLiveData(GasmandashboardHomeModel())

  var navArguments: Bundle? = null
}
