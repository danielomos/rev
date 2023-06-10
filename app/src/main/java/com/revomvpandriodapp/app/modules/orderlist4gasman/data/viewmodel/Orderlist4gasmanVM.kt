package com.revomvpandriodapp.app.modules.orderlist4gasman.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.orderlist4gasman.`data`.model.GasmanmainordrRowModel
import com.revomvpandriodapp.app.modules.orderlist4gasman.`data`.model.Orderlist4gasmanModel
import com.revomvpandriodapp.app.network.models.fetchall.FetchAllResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class Orderlist4gasmanVM : ViewModel(), KoinComponent {
  val orderlist4gasmanModel: MutableLiveData<Orderlist4gasmanModel> =
      MutableLiveData(Orderlist4gasmanModel())


  var navArguments: Bundle? = null


  val gasmanmainordrList: MutableLiveData<MutableList<GasmanmainordrRowModel>> =
      MutableLiveData(mutableListOf())


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val fetchAllLiveData: MutableLiveData<Response<FetchAllResponse>> =
      MutableLiveData<Response<FetchAllResponse>>()

  fun callFetchAllApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      fetchAllLiveData.postValue(
      networkRepository.fetchAll(
      contentType = """application/json""",
          authorization = prefs.getAccessToken()
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindFetchAllResponse(response: FetchAllResponse) {
    val orderlist4gasmanModelValue = orderlist4gasmanModel.value ?:Orderlist4gasmanModel()
    val recyclerGasmanmainordr = response.payload?.map {
      GasmanmainordrRowModel(
      txtWeight = it?.orderKg.toString(),
      txt12KgTextFormFiOne = it?.orderAmount.toString(),
      txt10042023Text = it?.orderCreatedDate.toString(),
      txtPendingTextFor = it?.orderStatus.toString(),
      )
      }?.toMutableList()
      gasmanmainordrList.value = recyclerGasmanmainordr
      orderlist4gasmanModel.value = orderlist4gasmanModelValue
    }
  }
