package com.revomvpandriodapp.app.modules.gasrequest.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.gasrequest.`data`.model.GasrequestModel
import com.revomvpandriodapp.app.network.models.createrefill.CreateRefillRequest
import com.revomvpandriodapp.app.network.models.createrefill.CreateRefillResponse
import com.revomvpandriodapp.app.network.models.createtotal.CreateTotalRequest
import com.revomvpandriodapp.app.network.models.createtotal.CreateTotalResponse
import com.revomvpandriodapp.app.network.models.fetchdetail.FetchDetailResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.Unit
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class GasrequestVM : ViewModel(), KoinComponent {
  val gasrequestModel: MutableLiveData<GasrequestModel> = MutableLiveData(GasrequestModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val createRefillLiveData: MutableLiveData<Response<CreateRefillResponse>> =
      MutableLiveData<Response<CreateRefillResponse>>()


  val fetchDetailLiveData: MutableLiveData<Response<FetchDetailResponse>> =
      MutableLiveData<Response<FetchDetailResponse>>()


  val createTotalLiveData: MutableLiveData<Response<CreateTotalResponse>> =
      MutableLiveData<Response<CreateTotalResponse>>()

  fun callCreateTotalApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createTotalLiveData.postValue(
      networkRepository.createTotal(
      contentType = """application/json""",
          authorization = prefs.getAccessToken(),
          createTotalRequest = CreateTotalRequest(
          refillingQuantity = gasrequestModel.value?.etLastnameValue,
          sellerId = navArguments?.getString("sellerId"))
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreateTotalResponse(response: CreateTotalResponse) {
    val gasrequestModelValue = gasrequestModel.value ?:GasrequestModel()
    gasrequestModelValue.txt0000 = response.payload.toString()
    gasrequestModel.value = gasrequestModelValue
  }

  fun callFetchDetailApi(): Unit {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      fetchDetailLiveData.postValue(
      networkRepository.fetchDetail(
      contentType = """application/json""",
      authorization = prefs.getAccessToken(),
      sellerId = navArguments?.getString("sellerId")
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindFetchDetailResponse(response: FetchDetailResponse): Unit {
    val gasrequestModelValue = gasrequestModel.value ?:GasrequestModel()
    gasrequestModelValue.txtRevoGasShop = response.payload?.sellerName.toString()
    prefs.setSellerAddress(response.payload?.sellerAddress)
    gasrequestModelValue.txtLanguageOne = response.payload?.sellerFullName.toString()
    gasrequestModelValue.txtLanguageThree = response.payload?.sellerSellingPrice.toString()
    gasrequestModelValue.txtLanguageFour = response.payload?.sellerDeliveryCharge.toString()
    gasrequestModel.value = gasrequestModelValue
  }

  fun callCreateRefillApi(): Unit {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createRefillLiveData.postValue(
      networkRepository.createRefill(
      contentType = """application/json""",
      authorization = prefs.getAccessToken(),
      createRefillRequest = CreateRefillRequest(sellerId = navArguments?.getString("sellerId"),
      requestedQuantity = gasrequestModel.value?.etLastnameValue)
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreateRefillResponse(response: CreateRefillResponse): Unit {
    val gasrequestModelValue = gasrequestModel.value ?:GasrequestModel()
    prefs.setMessage(response.payload)
    gasrequestModel.value = gasrequestModelValue
  }
}
