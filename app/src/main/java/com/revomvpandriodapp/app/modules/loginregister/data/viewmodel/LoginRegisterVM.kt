package com.revomvpandriodapp.app.modules.loginregister.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.loginregister.`data`.model.LoginRegisterModel
import org.koin.core.KoinComponent

class LoginRegisterVM : ViewModel(), KoinComponent {
  val loginRegisterModel: MutableLiveData<LoginRegisterModel> =
      MutableLiveData(LoginRegisterModel())

  var navArguments: Bundle? = null
}
