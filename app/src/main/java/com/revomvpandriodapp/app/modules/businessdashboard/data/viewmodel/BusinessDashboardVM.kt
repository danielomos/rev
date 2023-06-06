package com.revomvpandriodapp.app.modules.businessdashboard.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.businessdashboard.`data`.model.BusinessDashboardModel
import com.revomvpandriodapp.app.modules.businessdashboard.`data`.model.GasmanordersRowModel
import com.revomvpandriodapp.app.modules.businessdashboard.`data`.model.ListiconfortynineRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class BusinessDashboardVM : ViewModel(), KoinComponent {
  val businessDashboardModel: MutableLiveData<BusinessDashboardModel> =
      MutableLiveData(BusinessDashboardModel())

  var navArguments: Bundle? = null

  val listiconfortynineList: MutableLiveData<MutableList<ListiconfortynineRowModel>> =
      MutableLiveData(mutableListOf())

  val gasmanordersList: MutableLiveData<MutableList<GasmanordersRowModel>> =
      MutableLiveData(mutableListOf())
}
