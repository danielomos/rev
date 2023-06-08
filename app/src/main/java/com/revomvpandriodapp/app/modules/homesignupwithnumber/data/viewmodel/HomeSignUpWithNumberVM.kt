package com.revomvpandriodapp.app.modules.homesignupwithnumber.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.homesignupwithnumber.`data`.model.HomeSignUpWithNumberModel
import com.revomvpandriodapp.app.network.models.createphonesignup1.CreatePhoneSignup1Request
import com.revomvpandriodapp.app.network.models.createphonesignup1.CreatePhoneSignup1Response
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeSignUpWithNumberVM : ViewModel(), KoinComponent {
  val homeSignUpWithNumberModel: MutableLiveData<HomeSignUpWithNumberModel> =
      MutableLiveData(HomeSignUpWithNumberModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  val createPhoneSignup1LiveData: MutableLiveData<Response<CreatePhoneSignup1Response>> =
      MutableLiveData<Response<CreatePhoneSignup1Response>>()

  private val prefs: PreferenceHelper by inject()

  fun callCreatePhoneSignup1Api() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createPhoneSignup1LiveData.postValue(
      networkRepository.createPhoneSignup1(
      contentType = """application/json""",
          createPhoneSignup1Request = CreatePhoneSignup1Request(
          firstName = homeSignUpWithNumberModel.value?.etFirstnameValue,
          lastName = homeSignUpWithNumberModel.value?.etLastnameValue,
          password = homeSignUpWithNumberModel.value?.etPasswordValue,
          phoneNumber = homeSignUpWithNumberModel.value?.etLanguageValue,
          address = homeSignUpWithNumberModel.value?.etAddressValue)
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreatePhoneSignup1Response(response: CreatePhoneSignup1Response) {
    val homeSignUpWithNumberModelValue = homeSignUpWithNumberModel.value
        ?:HomeSignUpWithNumberModel()
    prefs.setMessage(response.payload?.message)
    homeSignUpWithNumberModel.value = homeSignUpWithNumberModelValue
  }
}
