package com.revomvpandriodapp.app.modules.loginregister.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.loginregister.`data`.model.LoginregisterModel
import org.koin.core.KoinComponent

class LoginregisterVM : ViewModel(), KoinComponent {
  val loginregisterModel: MutableLiveData<LoginregisterModel> =
      MutableLiveData(LoginregisterModel())

  var navArguments: Bundle? = null
}
