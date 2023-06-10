package com.revomvpandriodapp.app.modules.retailercoveragearea.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.modules.retailercoveragearea.`data`.model.AreaslistRowModel
import com.revomvpandriodapp.app.modules.retailercoveragearea.`data`.model.RetailercoverageareaModel
import com.revomvpandriodapp.app.network.models.fetchareas.FetchAreasResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class RetailercoverageareaVM : ViewModel(), KoinComponent {
  val retailercoverageareaModel: MutableLiveData<RetailercoverageareaModel> =
      MutableLiveData(RetailercoverageareaModel())


  var navArguments: Bundle? = null


  val areaslistList: MutableLiveData<MutableList<AreaslistRowModel>> =
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
    val retailercoverageareaModelValue = retailercoverageareaModel.value
        ?:RetailercoverageareaModel()
    val recyclerAreaslist = response.payload?.map {
      AreaslistRowModel(
      txtLanguage = it?.areaName.toString(),
      )
      }?.toMutableList()
      areaslistList.value = recyclerAreaslist
      retailercoverageareaModel.value = retailercoverageareaModelValue
    }
  }
