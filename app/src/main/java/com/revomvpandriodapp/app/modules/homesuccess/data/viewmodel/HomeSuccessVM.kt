package com.revomvpandriodapp.app.modules.homesuccess.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.homesuccess.`data`.model.HomeSuccessModel
import org.koin.core.KoinComponent

class HomeSuccessVM : ViewModel(), KoinComponent {
  val homeSuccessModel: MutableLiveData<HomeSuccessModel> = MutableLiveData(HomeSuccessModel())

  var navArguments: Bundle? = null
}
