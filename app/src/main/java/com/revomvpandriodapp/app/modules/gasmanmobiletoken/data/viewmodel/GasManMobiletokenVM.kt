package com.revomvpandriodapp.app.modules.gasmanmobiletoken.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.gasmanmobiletoken.`data`.model.GasManMobiletokenModel
import com.revomvpandriodapp.app.network.models.createmobiletokenverification.CreateMobileTokenVerificationRequest
import com.revomvpandriodapp.app.network.models.createmobiletokenverification.CreateMobileTokenVerificationResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class GasManMobiletokenVM : ViewModel(), KoinComponent {
  val gasManMobiletokenModel: MutableLiveData<GasManMobiletokenModel> =
      MutableLiveData(GasManMobiletokenModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  val createMobileTokenVerificationLiveData:
      MutableLiveData<Response<CreateMobileTokenVerificationResponse>> =
      MutableLiveData<Response<CreateMobileTokenVerificationResponse>>()

  private val prefs: PreferenceHelper by inject()

  fun callCreateMobileTokenVerificationApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createMobileTokenVerificationLiveData.postValue(
      networkRepository.createMobileTokenVerification(
      contentType = """application/json""",
          createMobileTokenVerificationRequest = CreateMobileTokenVerificationRequest(
          token = gasManMobiletokenModel.value?.etGasManPhoneTValue)
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreateMobileTokenVerificationResponse(response: CreateMobileTokenVerificationResponse) {
    val gasManMobiletokenModelValue = gasManMobiletokenModel.value ?:GasManMobiletokenModel()
    prefs.setMessage(response.payload?.message)
    gasManMobiletokenModel.value = gasManMobiletokenModelValue
  }
}
