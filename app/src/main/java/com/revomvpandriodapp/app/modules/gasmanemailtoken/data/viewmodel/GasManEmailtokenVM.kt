package com.revomvpandriodapp.app.modules.gasmanemailtoken.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.gasmanemailtoken.`data`.model.GasManEmailtokenModel
import com.revomvpandriodapp.app.network.models.createmobiletokenverification.CreateMobileTokenVerificationRequest
import com.revomvpandriodapp.app.network.models.createmobiletokenverification.CreateMobileTokenVerificationResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class GasManEmailtokenVM : ViewModel(), KoinComponent {
  val gasManEmailtokenModel: MutableLiveData<GasManEmailtokenModel> =
      MutableLiveData(GasManEmailtokenModel())


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
          token = gasManEmailtokenModel.value?.etGasManEmailTValue)
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreateMobileTokenVerificationResponse(response: CreateMobileTokenVerificationResponse) {
    val gasManEmailtokenModelValue = gasManEmailtokenModel.value ?:GasManEmailtokenModel()
    prefs.setMessage(response.payload?.message)
    gasManEmailtokenModel.value = gasManEmailtokenModelValue
  }
}
