package com.revomvpandriodapp.app.modules.businessdashboardcontainer.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.businessdashboardcontainer.`data`.model.BusinessDashboardContainerModel
import org.koin.core.KoinComponent

class BusinessDashboardContainerVM : ViewModel(), KoinComponent {
  val businessDashboardContainerModel: MutableLiveData<BusinessDashboardContainerModel> =
      MutableLiveData(BusinessDashboardContainerModel())

  var navArguments: Bundle? = null
}
