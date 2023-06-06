package com.revomvpandriodapp.app.modules.businessemailtoken.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.businessemailtoken.`data`.model.BusinessEmailtokenModel
import org.koin.core.KoinComponent

class BusinessEmailtokenVM : ViewModel(), KoinComponent {
  val businessEmailtokenModel: MutableLiveData<BusinessEmailtokenModel> =
      MutableLiveData(BusinessEmailtokenModel())

  var navArguments: Bundle? = null
}
