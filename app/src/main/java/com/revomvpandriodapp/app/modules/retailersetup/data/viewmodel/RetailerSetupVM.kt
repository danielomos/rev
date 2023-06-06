package com.revomvpandriodapp.app.modules.retailersetup.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.retailersetup.`data`.model.RetailerSetupModel
import com.revomvpandriodapp.app.network.models.createsetup.CreateSetupRequest
import com.revomvpandriodapp.app.network.models.createsetup.CreateSetupResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class RetailerSetupVM : ViewModel(), KoinComponent {
  val retailerSetupModel: MutableLiveData<RetailerSetupModel> =
      MutableLiveData(RetailerSetupModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val createSetupLiveData: MutableLiveData<Response<CreateSetupResponse>> =
      MutableLiveData<Response<CreateSetupResponse>>()

  fun callCreateSetupApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createSetupLiveData.postValue(
      networkRepository.createSetup(
      contentType = """application/json""",
          authorization = prefs.getAccessToken(),
          createSetupRequest = CreateSetupRequest(
          sellingPrice = retailerSetupModel.value?.etLastnameValue,
          address = retailerSetupModel.value?.etFirstnameValue,
          deliveringPrice = retailerSetupModel.value?.etLanguageValue,
          serviceAreaId = navArguments?.getString("areaName"),
          stockLevel = retailerSetupModel.value?.etFirstnameOneValue)
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreateSetupResponse(response: CreateSetupResponse) {
    val retailerSetupModelValue = retailerSetupModel.value ?:RetailerSetupModel()
    prefs.setMessage(response.payload)
    retailerSetupModel.value = retailerSetupModelValue
  }
}
