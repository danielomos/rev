package com.revomvpandriodapp.app.modules.retailersetstock.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.retailersetstock.`data`.model.RetailersetstockModel
import com.revomvpandriodapp.app.network.models.createupdate.CreateUpdateRequest
import com.revomvpandriodapp.app.network.models.createupdate.CreateUpdateResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class RetailersetstockVM : ViewModel(), KoinComponent {
  val retailersetstockModel: MutableLiveData<RetailersetstockModel> =
      MutableLiveData(RetailersetstockModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val createUpdateLiveData: MutableLiveData<Response<CreateUpdateResponse>> =
      MutableLiveData<Response<CreateUpdateResponse>>()

  fun callCreateUpdateApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createUpdateLiveData.postValue(
      networkRepository.createUpdate(
      contentType = """application/json""",
          authorization = prefs.getAccessToken(),
          createUpdateRequest = CreateUpdateRequest(
          sellingPrice = retailersetstockModel.value?.etLastnameValue,
          quantity = retailersetstockModel.value?.etFirstnameValue)
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreateUpdateResponse(response: CreateUpdateResponse) {
    val retailersetstockModelValue = retailersetstockModel.value ?:RetailersetstockModel()
    prefs.setPayload(response.payload)
    retailersetstockModel.value = retailersetstockModelValue
  }
}
