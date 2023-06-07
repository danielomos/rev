package com.revomvpandriodapp.app.modules.locaton.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.locaton.`data`.model.LocatonModel
import org.koin.core.KoinComponent

class LocatonVM : ViewModel(), KoinComponent {
  val locatonModel: MutableLiveData<LocatonModel> = MutableLiveData(LocatonModel())

  var navArguments: Bundle? = null
}
