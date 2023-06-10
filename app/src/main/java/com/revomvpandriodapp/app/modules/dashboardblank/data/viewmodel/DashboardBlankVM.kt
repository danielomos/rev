package com.revomvpandriodapp.app.modules.dashboardblank.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.dashboardblank.`data`.model.DashboardBlankModel
import org.koin.core.KoinComponent

class DashboardBlankVM : ViewModel(), KoinComponent {
  val dashboardBlankModel: MutableLiveData<DashboardBlankModel> =
      MutableLiveData(DashboardBlankModel())

  var navArguments: Bundle? = null
}
