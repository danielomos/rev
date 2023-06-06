package com.revomvpandriodapp.app.modules.gasmansignupwithemail.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.gasmansignupwithemail.`data`.model.GasManSignUpWithEmailModel
import com.revomvpandriodapp.app.network.models.createemailsignup.CreateEmailSignupRequest
import com.revomvpandriodapp.app.network.models.createemailsignup.CreateEmailSignupResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class GasManSignUpWithEmailVM : ViewModel(), KoinComponent {
  val gasManSignUpWithEmailModel: MutableLiveData<GasManSignUpWithEmailModel> =
      MutableLiveData(GasManSignUpWithEmailModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  val createEmailSignupLiveData: MutableLiveData<Response<CreateEmailSignupResponse>> =
      MutableLiveData<Response<CreateEmailSignupResponse>>()

  private val prefs: PreferenceHelper by inject()

  fun callCreateEmailSignupApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createEmailSignupLiveData.postValue(
      networkRepository.createEmailSignup(
      contentType = """application/json""",
          createEmailSignupRequest = CreateEmailSignupRequest(
          firstName = gasManSignUpWithEmailModel.value?.etFirstnameValue,
          lastName = gasManSignUpWithEmailModel.value?.etLastnameValue,
          password = gasManSignUpWithEmailModel.value?.etPasssordValue,
          address = gasManSignUpWithEmailModel.value?.etShopnameOneValue,
          shopName = gasManSignUpWithEmailModel.value?.etShopnameValue,
          email = gasManSignUpWithEmailModel.value?.etLanguageValue)
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreateEmailSignupResponse(response: CreateEmailSignupResponse) {
    val gasManSignUpWithEmailModelValue = gasManSignUpWithEmailModel.value
        ?:GasManSignUpWithEmailModel()
    prefs.setMessage(response.payload?.message)
    gasManSignUpWithEmailModel.value = gasManSignUpWithEmailModelValue
  }
}
