package com.revomvpandriodapp.app.modules.cusorderslist.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.cusorderslist.`data`.model.CusorderslistModel
import com.revomvpandriodapp.app.modules.cusorderslist.`data`.model.Listiconfortynine1RowModel
import com.revomvpandriodapp.app.network.models.fetchall1.FetchAll1Response
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class CusorderslistVM : ViewModel(), KoinComponent {
  val cusorderslistModel: MutableLiveData<CusorderslistModel> =
      MutableLiveData(CusorderslistModel())


  var navArguments: Bundle? = null


  val listiconfortynineList: MutableLiveData<MutableList<Listiconfortynine1RowModel>> =
      MutableLiveData(mutableListOf())


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val fetchAll1LiveData: MutableLiveData<Response<FetchAll1Response>> =
      MutableLiveData<Response<FetchAll1Response>>()

  fun callFetchAll1Api() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      fetchAll1LiveData.postValue(
      networkRepository.fetchAll1(
      contentType = """application/json""",
          authorization = prefs.getAccessToken()
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindFetchAll1Response(response: FetchAll1Response) {
    val cusorderslistModelValue = cusorderslistModel.value ?:CusorderslistModel()
    val recyclerListiconfortynine = response.payload?.map {
      Listiconfortynine1RowModel(
      txt12KgTextFormFiOne = it?.orderAmount.toString(),
      txtWeight = it?.orderKg.toString(),
      txt10042023Text = it?.orderCreatedDate.toString(),
      txtPendingTextFor = it?.orderStatus.toString(),
      )
      }?.toMutableList()
      listiconfortynineList.value = recyclerListiconfortynine
      cusorderslistModel.value = cusorderslistModelValue
    }
  }
