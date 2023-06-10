package com.revomvpandriodapp.app.modules.login.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.login.`data`.model.LoginModel
import com.revomvpandriodapp.app.network.models.createtoken.CreateTokenRequest
import com.revomvpandriodapp.app.network.models.createtoken.CreateTokenResponse
import com.revomvpandriodapp.app.network.models.fetchtype.FetchTypeResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.Unit
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class LoginVM : ViewModel(), KoinComponent {
  val loginModel: MutableLiveData<LoginModel> = MutableLiveData(LoginModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


  private val networkRepository: NetworkRepository by inject()

  val createTokenLiveData: MutableLiveData<Response<CreateTokenResponse>> =
      MutableLiveData<Response<CreateTokenResponse>>()


  private val prefs: PreferenceHelper by inject()

  val fetchTypeLiveData: MutableLiveData<Response<FetchTypeResponse>> =
      MutableLiveData<Response<FetchTypeResponse>>()

  fun callFetchTypeApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      fetchTypeLiveData.postValue(
      networkRepository.fetchType(
      contentType = """application/json""",
          authorization = prefs.getAccessToken()
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindFetchTypeResponse(response: FetchTypeResponse) {
    val loginModelValue = loginModel.value ?:LoginModel()
    prefs.setUserType(response.payload?.userType)
    loginModel.value = loginModelValue
  }

  fun callCreateTokenApi(): Unit {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createTokenLiveData.postValue(
      networkRepository.createToken(
      contentType = """application/json""",
      createTokenRequest = CreateTokenRequest(
      password = loginModel.value?.etPasswordValue,
      username = loginModel.value?.etEmailphoneValue)
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreateTokenResponse(response: CreateTokenResponse): Unit {
    val loginModelValue = loginModel.value ?:LoginModel()
    prefs.setType(response.payload?.type)
    prefs.setAccessToken(response.payload?.accessToken)
    prefs.setAccExpiredAt(response.payload?.accExpiredAt)
    prefs.setRefreshToken(response.payload?.refreshToken)
    prefs.setRefExpiredAt(response.payload?.refExpiredAt)
    loginModel.value = loginModelValue
  }
}
