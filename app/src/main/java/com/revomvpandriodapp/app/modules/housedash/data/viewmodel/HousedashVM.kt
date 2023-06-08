package com.revomvpandriodapp.app.modules.housedash.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.housedash.`data`.model.Gasmanorder2RowModel
import com.revomvpandriodapp.app.modules.housedash.`data`.model.HousedashModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class HousedashVM : ViewModel(), KoinComponent {
  val housedashModel: MutableLiveData<HousedashModel> = MutableLiveData(HousedashModel())

  var navArguments: Bundle? = null

  val gasmanorderList: MutableLiveData<MutableList<Gasmanorder2RowModel>> =
      MutableLiveData(mutableListOf())
}
