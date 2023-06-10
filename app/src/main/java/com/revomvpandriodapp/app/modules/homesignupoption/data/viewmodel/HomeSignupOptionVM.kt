package com.revomvpandriodapp.app.modules.homesignupoption.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.homesignupoption.`data`.model.HomeSignupOptionModel
import org.koin.core.KoinComponent

class HomeSignupOptionVM : ViewModel(), KoinComponent {
  val homeSignupOptionModel: MutableLiveData<HomeSignupOptionModel> =
      MutableLiveData(HomeSignupOptionModel())

  var navArguments: Bundle? = null
}
