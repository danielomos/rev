package com.revomvpandriodapp.app.modules.orderlist4gasman.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.orderlist4gasman.`data`.model.GasmanmainordrRowModel
import com.revomvpandriodapp.app.modules.orderlist4gasman.`data`.model.Orderlist4gasmanModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class Orderlist4gasmanVM : ViewModel(), KoinComponent {
  val orderlist4gasmanModel: MutableLiveData<Orderlist4gasmanModel> =
      MutableLiveData(Orderlist4gasmanModel())

  var navArguments: Bundle? = null

  val gasmanmainordrList: MutableLiveData<MutableList<GasmanmainordrRowModel>> =
      MutableLiveData(mutableListOf())
}
