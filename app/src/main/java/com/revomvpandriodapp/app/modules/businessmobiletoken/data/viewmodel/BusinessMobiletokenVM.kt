package com.revomvpandriodapp.app.modules.businessmobiletoken.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.businessmobiletoken.`data`.model.BusinessMobiletokenModel
import org.koin.core.KoinComponent

class BusinessMobiletokenVM : ViewModel(), KoinComponent {
  val businessMobiletokenModel: MutableLiveData<BusinessMobiletokenModel> =
      MutableLiveData(BusinessMobiletokenModel())

  var navArguments: Bundle? = null
}
