package com.revomvpandriodapp.app.modules.orderlist4household.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.orderlist4household.`data`.model.Listiconfortynine1RowModel
import com.revomvpandriodapp.app.modules.orderlist4household.`data`.model.Orderlist4householdModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class Orderlist4householdVM : ViewModel(), KoinComponent {
  val orderlist4householdModel: MutableLiveData<Orderlist4householdModel> =
      MutableLiveData(Orderlist4householdModel())

  var navArguments: Bundle? = null

  val listiconfortynineList: MutableLiveData<MutableList<Listiconfortynine1RowModel>> =
      MutableLiveData(mutableListOf())
}
