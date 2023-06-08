package com.revomvpandriodapp.app.modules.dashboard4household.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.dashboard4household.`data`.model.Dashboard4householdModel
import com.revomvpandriodapp.app.network.models.fetchdetails1.FetchDetails1Response
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class Dashboard4householdVM : ViewModel(), KoinComponent {
  val dashboard4householdModel: MutableLiveData<Dashboard4householdModel> =
      MutableLiveData(Dashboard4householdModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val fetchDetails1LiveData: MutableLiveData<Response<FetchDetails1Response>> =
      MutableLiveData<Response<FetchDetails1Response>>()

  fun callFetchDetails1Api() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      fetchDetails1LiveData.postValue(
      networkRepository.fetchDetails1(
      contentType = """application/json""",
          authorization = prefs.getAccessToken()
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindFetchDetails1Response(response: FetchDetails1Response) {
    val dashboard4householdModelValue = dashboard4householdModel.value ?:Dashboard4householdModel()
    prefs.setOnBoarded(response.payload?.onBoarded)
    dashboard4householdModel.value = dashboard4householdModelValue
  }
}
