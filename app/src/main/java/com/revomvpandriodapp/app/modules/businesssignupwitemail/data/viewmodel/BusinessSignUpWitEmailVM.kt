package com.revomvpandriodapp.app.modules.businesssignupwitemail.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.businesssignupwitemail.`data`.model.BusinessSignUpWitEmailModel
import org.koin.core.KoinComponent

class BusinessSignUpWitEmailVM : ViewModel(), KoinComponent {
  val businessSignUpWitEmailModel: MutableLiveData<BusinessSignUpWitEmailModel> =
      MutableLiveData(BusinessSignUpWitEmailModel())

  var navArguments: Bundle? = null
}
