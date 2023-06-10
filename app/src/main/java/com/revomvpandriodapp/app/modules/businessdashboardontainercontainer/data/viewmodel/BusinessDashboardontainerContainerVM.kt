package com.revomvpandriodapp.app.modules.businessdashboardontainercontainer.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.businessdashboardontainercontainer.`data`.model.BusinessDashboardontainerContainerModel
import org.koin.core.KoinComponent

class BusinessDashboardontainerContainerVM : ViewModel(), KoinComponent {
  val businessDashboardontainerContainerModel:
      MutableLiveData<BusinessDashboardontainerContainerModel> =
      MutableLiveData(BusinessDashboardontainerContainerModel())

  var navArguments: Bundle? = null
}
