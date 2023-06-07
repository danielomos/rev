package com.revomvpandriodapp.app.modules.gasmansignupwithnumber.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.gasmansignupwithnumber.`data`.model.GasManSignUpWithNumberModel
import com.revomvpandriodapp.app.network.models.createphonesignup.CreatePhoneSignupRequest
import com.revomvpandriodapp.app.network.models.createphonesignup.CreatePhoneSignupResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class GasManSignUpWithNumberVM : ViewModel(), KoinComponent {
  val gasManSignUpWithNumberModel: MutableLiveData<GasManSignUpWithNumberModel> =
      MutableLiveData(GasManSignUpWithNumberModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  val createPhoneSignupLiveData: MutableLiveData<Response<CreatePhoneSignupResponse>> =
      MutableLiveData<Response<CreatePhoneSignupResponse>>()

  private val prefs: PreferenceHelper by inject()

  fun callCreatePhoneSignupApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createPhoneSignupLiveData.postValue(
      networkRepository.createPhoneSignup(
      contentType = """application/json""",
          createPhoneSignupRequest = CreatePhoneSignupRequest(
          lastName = gasManSignUpWithNumberModel.value?.etLastnameValue,
          firstName = gasManSignUpWithNumberModel.value?.etFirstnameValue,
          password = gasManSignUpWithNumberModel.value?.etPasswordValue,
          phoneNumber = gasManSignUpWithNumberModel.value?.etLanguageValue,
          address = gasManSignUpWithNumberModel.value?.etShopaddressValue,
          shopName = gasManSignUpWithNumberModel.value?.etShopnameValue)
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreatePhoneSignupResponse(response: CreatePhoneSignupResponse) {
    val gasManSignUpWithNumberModelValue = gasManSignUpWithNumberModel.value
        ?:GasManSignUpWithNumberModel()
    prefs.setMessage(response.payload?.message)
    gasManSignUpWithNumberModel.value = gasManSignUpWithNumberModelValue
  }
}
