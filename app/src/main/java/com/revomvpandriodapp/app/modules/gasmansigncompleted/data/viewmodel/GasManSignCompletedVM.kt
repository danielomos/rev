package com.revomvpandriodapp.app.modules.gasmansigncompleted.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.gasmansigncompleted.`data`.model.GasManSignCompletedModel
import org.koin.core.KoinComponent

class GasManSignCompletedVM : ViewModel(), KoinComponent {
  val gasManSignCompletedModel: MutableLiveData<GasManSignCompletedModel> =
      MutableLiveData(GasManSignCompletedModel())

  var navArguments: Bundle? = null
}
