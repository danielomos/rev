package com.revomvpandriodapp.app.modules.businesssignupwithnumber.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.businesssignupwithnumber.`data`.model.BusinessSignUpWithNumberModel
import org.koin.core.KoinComponent

class BusinessSignUpWithNumberVM : ViewModel(), KoinComponent {
  val businessSignUpWithNumberModel: MutableLiveData<BusinessSignUpWithNumberModel> =
      MutableLiveData(BusinessSignUpWithNumberModel())

  var navArguments: Bundle? = null
}
