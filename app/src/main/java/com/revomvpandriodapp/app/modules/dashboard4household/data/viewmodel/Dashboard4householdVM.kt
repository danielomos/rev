package com.revomvpandriodapp.app.modules.dashboard4household.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.dashboard4household.`data`.model.Dashboard4householdModel
import org.koin.core.KoinComponent

class Dashboard4householdVM : ViewModel(), KoinComponent {
  val dashboard4householdModel: MutableLiveData<Dashboard4householdModel> =
      MutableLiveData(Dashboard4householdModel())

  var navArguments: Bundle? = null
}
