package com.revomvpandriodapp.app.modules.thankyou.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.thankyou.`data`.model.ThankYouModel
import org.koin.core.KoinComponent

class ThankYouVM : ViewModel(), KoinComponent {
  val thankYouModel: MutableLiveData<ThankYouModel> = MutableLiveData(ThankYouModel())

  var navArguments: Bundle? = null
}
