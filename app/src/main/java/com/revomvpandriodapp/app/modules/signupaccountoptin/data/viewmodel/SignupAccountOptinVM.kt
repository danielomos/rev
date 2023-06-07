package com.revomvpandriodapp.app.modules.signupaccountoptin.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.signupaccountoptin.`data`.model.SignupAccountOptinModel
import org.koin.core.KoinComponent

class SignupAccountOptinVM : ViewModel(), KoinComponent {
  val signupAccountOptinModel: MutableLiveData<SignupAccountOptinModel> =
      MutableLiveData(SignupAccountOptinModel())

  var navArguments: Bundle? = null
}
