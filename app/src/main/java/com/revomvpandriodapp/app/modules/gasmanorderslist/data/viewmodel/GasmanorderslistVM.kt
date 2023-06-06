package com.revomvpandriodapp.app.modules.gasmanorderslist.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.gasmanorderslist.`data`.model.GasmanmainordrRowModel
import com.revomvpandriodapp.app.modules.gasmanorderslist.`data`.model.GasmanorderslistModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class GasmanorderslistVM : ViewModel(), KoinComponent {
  val gasmanorderslistModel: MutableLiveData<GasmanorderslistModel> =
      MutableLiveData(GasmanorderslistModel())

  var navArguments: Bundle? = null

  val gasmanmainordrList: MutableLiveData<MutableList<GasmanmainordrRowModel>> =
      MutableLiveData(mutableListOf())
}
