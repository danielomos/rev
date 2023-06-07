package com.revomvpandriodapp.app.modules.householddashboardhomecontainer.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.householddashboardhomecontainer.`data`.model.HouseholddashboardHomeContainerModel
import org.koin.core.KoinComponent

class HouseholddashboardHomeContainerVM : ViewModel(), KoinComponent {
  val householddashboardHomeContainerModel: MutableLiveData<HouseholddashboardHomeContainerModel> =
      MutableLiveData(HouseholddashboardHomeContainerModel())

  var navArguments: Bundle? = null
}
