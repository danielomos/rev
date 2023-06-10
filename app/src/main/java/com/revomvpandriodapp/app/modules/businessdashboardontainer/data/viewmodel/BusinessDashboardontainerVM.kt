package com.revomvpandriodapp.app.modules.businessdashboardontainer.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.businessdashboardontainer.`data`.model.BusinessDashboardontainerModel
import com.revomvpandriodapp.app.modules.businessdashboardontainer.`data`.model.GasmanordersRowModel
import com.revomvpandriodapp.app.modules.businessdashboardontainer.`data`.model.ListiconfortynineRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class BusinessDashboardontainerVM : ViewModel(), KoinComponent {
  val businessDashboardontainerModel: MutableLiveData<BusinessDashboardontainerModel> =
      MutableLiveData(BusinessDashboardontainerModel())

  var navArguments: Bundle? = null

  val listiconfortynineList: MutableLiveData<MutableList<ListiconfortynineRowModel>> =
      MutableLiveData(mutableListOf())

  val gasmanordersList: MutableLiveData<MutableList<GasmanordersRowModel>> =
      MutableLiveData(mutableListOf())
}
