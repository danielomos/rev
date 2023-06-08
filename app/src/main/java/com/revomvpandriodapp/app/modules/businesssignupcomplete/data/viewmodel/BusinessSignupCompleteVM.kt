package com.revomvpandriodapp.app.modules.businesssignupcomplete.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.businesssignupcomplete.`data`.model.BusinessSignupCompleteModel
import org.koin.core.KoinComponent

class BusinessSignupCompleteVM : ViewModel(), KoinComponent {
  val businessSignupCompleteModel: MutableLiveData<BusinessSignupCompleteModel> =
      MutableLiveData(BusinessSignupCompleteModel())

  var navArguments: Bundle? = null
}
