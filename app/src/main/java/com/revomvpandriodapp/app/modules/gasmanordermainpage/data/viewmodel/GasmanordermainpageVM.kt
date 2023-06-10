package com.revomvpandriodapp.app.modules.gasmanordermainpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.gasmanordermainpage.`data`.model.GasmanordermainpageModel
import org.koin.core.KoinComponent

class GasmanordermainpageVM : ViewModel(), KoinComponent {
  val gasmanordermainpageModel: MutableLiveData<GasmanordermainpageModel> =
      MutableLiveData(GasmanordermainpageModel())

  var navArguments: Bundle? = null
}
