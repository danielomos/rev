package com.revomvpandriodapp.app.modules.suplist.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.suplist.`data`.model.SuplistModel
import com.revomvpandriodapp.app.modules.suplist.`data`.model.SuplistRowModel
import com.revomvpandriodapp.app.network.models.fetchdistributors.FetchDistributorsResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class SuplistVM : ViewModel(), KoinComponent {
  val suplistModel: MutableLiveData<SuplistModel> = MutableLiveData(SuplistModel())


  var navArguments: Bundle? = null


  val suplistList: MutableLiveData<MutableList<SuplistRowModel>> = MutableLiveData(mutableListOf())


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val fetchDistributorsLiveData: MutableLiveData<Response<FetchDistributorsResponse>> =
      MutableLiveData<Response<FetchDistributorsResponse>>()

  fun callFetchDistributorsApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      fetchDistributorsLiveData.postValue(
      networkRepository.fetchDistributors(
      contentType = """application/json""",
          authorization = prefs.getAccessToken(),
          serviceArea = navArguments?.getString("areaName")
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindFetchDistributorsResponse(response: FetchDistributorsResponse) {
    val suplistModelValue = suplistModel.value ?:SuplistModel()
    val recyclerSuplist = response.payload?.distributions?.map {
      SuplistRowModel(
      txtRevoGas = it?.sellerName.toString(),
      txt10YabaRoad = it?.sellerAddress.toString(),
      etFrame247Value = it?.sellerSellingPrice.toString(),
      )
      }?.toMutableList()
      suplistList.value = recyclerSuplist
      suplistModel.value = suplistModelValue
    }
  }
