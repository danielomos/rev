package com.revomvpandriodapp.app.modules.gasmanorderslist.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.gasmanorderslist.`data`.model.GasmanmainordrRowModel
import com.revomvpandriodapp.app.modules.gasmanorderslist.`data`.model.GasmanorderslistModel
import com.revomvpandriodapp.app.network.models.fetchall.FetchAllResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class GasmanorderslistVM : ViewModel(), KoinComponent {
  val gasmanorderslistModel: MutableLiveData<GasmanorderslistModel> =
      MutableLiveData(GasmanorderslistModel())


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
    val gasmanorderslistModelValue = gasmanorderslistModel.value ?:GasmanorderslistModel()
    val recyclerGasmanmainordr = response.payload?.map {
      GasmanmainordrRowModel(
      txt12KgTextFormFiOne = it?.orderAmount.toString(),
      txtWeight = it?.orderKg.toString(),
      txt10042023Text = it?.orderCreatedDate.toString(),
      txtPendingTextFor = it?.orderStatus.toString(),
      )
      }?.toMutableList()
      gasmanmainordrList.value = recyclerGasmanmainordr
      gasmanorderslistModel.value = gasmanorderslistModelValue
    }
  }
