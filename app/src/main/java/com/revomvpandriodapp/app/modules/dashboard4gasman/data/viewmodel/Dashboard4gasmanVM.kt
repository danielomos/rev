package com.revomvpandriodapp.app.modules.dashboard4gasman.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.dashboard4gasman.`data`.model.Dashboard4gasmanModel
import com.revomvpandriodapp.app.network.models.fetchdetails.FetchDetailsResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class Dashboard4gasmanVM : ViewModel(), KoinComponent {
  val dashboard4gasmanModel: MutableLiveData<Dashboard4gasmanModel> =
      MutableLiveData(Dashboard4gasmanModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val fetchDetailsLiveData: MutableLiveData<Response<FetchDetailsResponse>> =
      MutableLiveData<Response<FetchDetailsResponse>>()

  fun callFetchDetailsApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      fetchDetailsLiveData.postValue(
      networkRepository.fetchDetails(
      contentType = """application/json""",
          authorization = prefs.getAccessToken()
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindFetchDetailsResponse(response: FetchDetailsResponse) {
    val dashboard4gasmanModelValue = dashboard4gasmanModel.value ?:Dashboard4gasmanModel()
    prefs.setOnBoarded(response.payload?.onBoarded)
    dashboard4gasmanModel.value = dashboard4gasmanModelValue
  }
}
