package com.revomvpandriodapp.app.modules.gasmansignupoption.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.gasmansignupoption.`data`.model.GasManSignupOptionModel
import org.koin.core.KoinComponent

class GasManSignupOptionVM : ViewModel(), KoinComponent {
  val gasManSignupOptionModel: MutableLiveData<GasManSignupOptionModel> =
      MutableLiveData(GasManSignupOptionModel())

  var navArguments: Bundle? = null
}
