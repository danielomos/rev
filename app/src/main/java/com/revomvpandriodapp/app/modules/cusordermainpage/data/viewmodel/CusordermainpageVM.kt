package com.revomvpandriodapp.app.modules.cusordermainpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.cusordermainpage.`data`.model.CusordermainpageModel
import org.koin.core.KoinComponent

class CusordermainpageVM : ViewModel(), KoinComponent {
  val cusordermainpageModel: MutableLiveData<CusordermainpageModel> =
      MutableLiveData(CusordermainpageModel())

  var navArguments: Bundle? = null
}
