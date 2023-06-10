package com.revomvpandriodapp.app.modules.businesssignupoption.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.businesssignupoption.`data`.model.BusinessSignupOptionModel
import org.koin.core.KoinComponent

class BusinessSignupOptionVM : ViewModel(), KoinComponent {
  val businessSignupOptionModel: MutableLiveData<BusinessSignupOptionModel> =
      MutableLiveData(BusinessSignupOptionModel())

  var navArguments: Bundle? = null
}
