package com.revomvpandriodapp.app.modules.noactionordergasmandetail.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.noactionordergasmandetail.`data`.model.NoactionordergasmandetailModel
import com.revomvpandriodapp.app.network.models.createaccept.CreateAcceptRequest
import com.revomvpandriodapp.app.network.models.createaccept.CreateAcceptResponse
import com.revomvpandriodapp.app.network.models.createdelivered.CreateDeliveredRequest
import com.revomvpandriodapp.app.network.models.createdelivered.CreateDeliveredResponse
import com.revomvpandriodapp.app.network.models.createpickup.CreatePickupRequest
import com.revomvpandriodapp.app.network.models.createpickup.CreatePickupResponse
import com.revomvpandriodapp.app.network.models.fetchid.FetchIdResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.Unit
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class NoactionordergasmandetailVM : ViewModel(), KoinComponent {
  val noactionordergasmandetailModel: MutableLiveData<NoactionordergasmandetailModel> =
      MutableLiveData(NoactionordergasmandetailModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val fetchIdLiveData: MutableLiveData<Response<FetchIdResponse>> =
      MutableLiveData<Response<FetchIdResponse>>()


  val createPickupLiveData: MutableLiveData<Response<CreatePickupResponse>> =
      MutableLiveData<Response<CreatePickupResponse>>()


  val createDeliveredLiveData: MutableLiveData<Response<CreateDeliveredResponse>> =
      MutableLiveData<Response<CreateDeliveredResponse>>()


  val createAcceptLiveData: MutableLiveData<Response<CreateAcceptResponse>> =
      MutableLiveData<Response<CreateAcceptResponse>>()

  fun callCreateAcceptApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createAcceptLiveData.postValue(
      networkRepository.createAccept(
      contentType = """application/json""",
          authorization = prefs.getAccessToken(),
          createAcceptRequest = CreateAcceptRequest(orderId = navArguments?.getString("orderId"))
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreateAcceptResponse(response: CreateAcceptResponse) {
    val noactionordergasmandetailModelValue = noactionordergasmandetailModel.value
        ?:NoactionordergasmandetailModel()
    prefs.setMessage(response.payload?.message)
    noactionordergasmandetailModel.value = noactionordergasmandetailModelValue
  }

  fun callCreateDeliveredApi(): Unit {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createDeliveredLiveData.postValue(
      networkRepository.createDelivered(
      contentType = """application/json""",
      authorization = prefs.getAccessToken(),
      createDeliveredRequest = CreateDeliveredRequest(orderId =
      navArguments?.getString("orderId"))
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreateDeliveredResponse(response: CreateDeliveredResponse): Unit {
    val noactionordergasmandetailModelValue = noactionordergasmandetailModel.value
    ?:NoactionordergasmandetailModel()
    prefs.setMessage(response.payload?.message)
    noactionordergasmandetailModel.value = noactionordergasmandetailModelValue
  }

  fun callCreatePickupApi(): Unit {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createPickupLiveData.postValue(
      networkRepository.createPickup(
      contentType = """application/json""",
      authorization = prefs.getAccessToken(),
      createPickupRequest = CreatePickupRequest(
      pickUpKg = noactionordergasmandetailModel.value?.etFrameThirtyEightValue,
      orderId = navArguments?.getString("orderId"))
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreatePickupResponse(response: CreatePickupResponse): Unit {
    val noactionordergasmandetailModelValue = noactionordergasmandetailModel.value
    ?:NoactionordergasmandetailModel()
    prefs.setMessage(response.payload?.message)
    prefs.setDeliveryKg(response.payload?.deliveryKg)
    noactionordergasmandetailModel.value = noactionordergasmandetailModelValue
  }

  fun callFetchIdApi(): Unit {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      fetchIdLiveData.postValue(
      networkRepository.fetchId(
      contentType = """application/json""",
      authorization = prefs.getAccessToken(),
      id = navArguments?.getString("orderId")
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindFetchIdResponse(response: FetchIdResponse): Unit {
    val noactionordergasmandetailModelValue = noactionordergasmandetailModel.value
    ?:NoactionordergasmandetailModel()
    noactionordergasmandetailModelValue.txt1010101010101 = response.payload?.orderCode.toString()
    noactionordergasmandetailModelValue.txtHibroWhats = response.payload?.orderAmount.toString()
    noactionordergasmandetailModelValue.txtWhatdoyouthi = response.payload?.orderKg.toString()
    noactionordergasmandetailModelValue.txtSendisachatThree = response.payload?.pickUpKg.toString()
    noactionordergasmandetailModelValue.txtSendisachatFour = response.payload?.deliveryKg.toString()
    noactionordergasmandetailModelValue.txtSendisachatTwo =
    response.payload?.orderCreatedDate.toString()
    noactionordergasmandetailModelValue.txtSendisachatFive =
    response.payload?.orderStatus.toString()
    noactionordergasmandetailModelValue.txtSendisachat =
    response.payload?.orderCustomerAddress.toString()
    noactionordergasmandetailModelValue.txtSendisachatOne =
    response.payload?.orderCusPhoneNumber.toString()
    noactionordergasmandetailModelValue.txtInteresttopar =
    response.payload?.orderCusFullName.toString()
    noactionordergasmandetailModel.value = noactionordergasmandetailModelValue
  }
}
