package com.revomvpandriodapp.app.modules.cusorderslist.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.cusorderslist.`data`.model.CusorderslistModel
import com.revomvpandriodapp.app.modules.cusorderslist.`data`.model.Listiconfortynine1RowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class CusorderslistVM : ViewModel(), KoinComponent {
  val cusorderslistModel: MutableLiveData<CusorderslistModel> =
      MutableLiveData(CusorderslistModel())

  var navArguments: Bundle? = null

  val listiconfortynineList: MutableLiveData<MutableList<Listiconfortynine1RowModel>> =
      MutableLiveData(mutableListOf())
}
