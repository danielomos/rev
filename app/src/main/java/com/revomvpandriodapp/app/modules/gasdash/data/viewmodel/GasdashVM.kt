package com.revomvpandriodapp.app.modules.gasdash.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.gasdash.`data`.model.GasdashModel
import com.revomvpandriodapp.app.modules.gasdash.`data`.model.Listiconfortynine2RowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class GasdashVM : ViewModel(), KoinComponent {
  val gasdashModel: MutableLiveData<GasdashModel> = MutableLiveData(GasdashModel())

  var navArguments: Bundle? = null

  val listiconfortynineList: MutableLiveData<MutableList<Listiconfortynine2RowModel>> =
      MutableLiveData(mutableListOf())
}
