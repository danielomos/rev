package com.revomvpandriodapp.app.modules.arealisting.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.modules.arealisting.`data`.model.AreaListingModel
import com.revomvpandriodapp.app.modules.arealisting.`data`.model.ListlanguageRowModel
import com.revomvpandriodapp.app.network.models.fetchareas.FetchAreasResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class AreaListingVM : ViewModel(), KoinComponent {
  val areaListingModel: MutableLiveData<AreaListingModel> = MutableLiveData(AreaListingModel())


  var navArguments: Bundle? = null


  val listlanguageList: MutableLiveData<MutableList<ListlanguageRowModel>> =
      MutableLiveData(mutableListOf())


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  val fetchAreasLiveData: MutableLiveData<Response<FetchAreasResponse>> =
      MutableLiveData<Response<FetchAreasResponse>>()

  fun callFetchAreasApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      fetchAreasLiveData.postValue(
      networkRepository.fetchAreas()
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindFetchAreasResponse(response: FetchAreasResponse) {
    val areaListingModelValue = areaListingModel.value ?:AreaListingModel()
    val recyclerListlanguage = response.payload?.map {
      ListlanguageRowModel(
      txtLanguage = it?.areaName.toString(),
      )
      }?.toMutableList()
      listlanguageList.value = recyclerListlanguage
      areaListingModel.value = areaListingModelValue
    }
  }
