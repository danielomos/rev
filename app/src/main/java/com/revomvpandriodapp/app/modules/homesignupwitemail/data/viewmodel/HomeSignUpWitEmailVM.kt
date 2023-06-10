package com.revomvpandriodapp.app.modules.homesignupwitemail.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.homesignupwitemail.`data`.model.HomeSignUpWitEmailModel
import com.revomvpandriodapp.app.network.models.createemailsignup1.CreateEmailSignup1Request
import com.revomvpandriodapp.app.network.models.createemailsignup1.CreateEmailSignup1Response
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeSignUpWitEmailVM : ViewModel(), KoinComponent {
  val homeSignUpWitEmailModel: MutableLiveData<HomeSignUpWitEmailModel> =
      MutableLiveData(HomeSignUpWitEmailModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  val createEmailSignup1LiveData: MutableLiveData<Response<CreateEmailSignup1Response>> =
      MutableLiveData<Response<CreateEmailSignup1Response>>()

  private val prefs: PreferenceHelper by inject()

  fun callCreateEmailSignup1Api() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createEmailSignup1LiveData.postValue(
      networkRepository.createEmailSignup1(
      contentType = """application/json""",
          createEmailSignup1Request = CreateEmailSignup1Request(
          firstName = homeSignUpWitEmailModel.value?.etFirstnameValue,
          lastName = homeSignUpWitEmailModel.value?.etLastnameValue,
          password = homeSignUpWitEmailModel.value?.etPasswordValue,
          address = homeSignUpWitEmailModel.value?.etEmailValue,
          email = homeSignUpWitEmailModel.value?.etLanguageValue)
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreateEmailSignup1Response(response: CreateEmailSignup1Response) {
    val homeSignUpWitEmailModelValue = homeSignUpWitEmailModel.value ?:HomeSignUpWitEmailModel()
    prefs.setMessage(response.payload?.message)
    homeSignUpWitEmailModel.value = homeSignUpWitEmailModelValue
  }
}
