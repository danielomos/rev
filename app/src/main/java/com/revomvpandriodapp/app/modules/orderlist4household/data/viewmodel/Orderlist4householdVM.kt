package com.revomvpandriodapp.app.modules.orderlist4household.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.orderlist4household.`data`.model.Listiconfortynine1RowModel
import com.revomvpandriodapp.app.modules.orderlist4household.`data`.model.Orderlist4householdModel
import com.revomvpandriodapp.app.network.models.fetchall1.FetchAll1Response
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class Orderlist4householdVM : ViewModel(), KoinComponent {
  val orderlist4householdModel: MutableLiveData<Orderlist4householdModel> =
      MutableLiveData(Orderlist4householdModel())


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
    val orderlist4householdModelValue = orderlist4householdModel.value ?:Orderlist4householdModel()
    val recyclerListiconfortynine = response.payload?.map {
      Listiconfortynine1RowModel(
      txt12KgTextFormFiOne = it?.orderAmount.toString(),
      txtWeight = it?.orderKg.toString(),
      txtPendingTextFor = it?.orderStatus.toString(),
      txt10042023Text = it?.orderCreatedDate.toString(),
      )
      }?.toMutableList()
      listiconfortynineList.value = recyclerListiconfortynine
      orderlist4householdModel.value = orderlist4householdModelValue
    }
  }
