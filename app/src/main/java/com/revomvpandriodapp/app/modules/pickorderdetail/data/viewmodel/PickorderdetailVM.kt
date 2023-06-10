package com.revomvpandriodapp.app.modules.pickorderdetail.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.pickorderdetail.`data`.model.PickorderdetailModel
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

class PickorderdetailVM : ViewModel(), KoinComponent {
  val pickorderdetailModel: MutableLiveData<PickorderdetailModel> =
      MutableLiveData(PickorderdetailModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val createPickupLiveData: MutableLiveData<Response<CreatePickupResponse>> =
      MutableLiveData<Response<CreatePickupResponse>>()


  val fetchIdLiveData: MutableLiveData<Response<FetchIdResponse>> =
      MutableLiveData<Response<FetchIdResponse>>()

  fun callFetchIdApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      fetchIdLiveData.postValue(
      networkRepository.fetchId(
      contentType = """application/json""",
          authorization = prefs.getAccessToken(),
          id = prefs.getOrderId()
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindFetchIdResponse(response: FetchIdResponse) {
    val pickorderdetailModelValue = pickorderdetailModel.value ?:PickorderdetailModel()
    prefs.setOrderId(response.payload?.orderId)
    pickorderdetailModelValue.txt1010101010101 = response.payload?.orderCode.toString()
    pickorderdetailModelValue.txtHibroWhats = response.payload?.orderAmount.toString()
    pickorderdetailModelValue.txtWhatdoyouthi = response.payload?.orderKg.toString()
    pickorderdetailModelValue.txtSendisachatThree = response.payload?.pickUpKg.toString()
    pickorderdetailModelValue.txtSendisachatFour = response.payload?.deliveryKg.toString()
    pickorderdetailModelValue.txtSendisachatTwo = response.payload?.orderCreatedDate.toString()
    pickorderdetailModelValue.txtSendisachatFive = response.payload?.orderStatus.toString()
    pickorderdetailModelValue.txtSendisachat = response.payload?.orderCustomerAddress.toString()
    pickorderdetailModelValue.txtSendisachatOne = response.payload?.orderCusPhoneNumber.toString()
    pickorderdetailModelValue.txtInteresttopar = response.payload?.orderCusFullName.toString()
    pickorderdetailModel.value = pickorderdetailModelValue
  }

  fun callCreatePickupApi(): Unit {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createPickupLiveData.postValue(
      networkRepository.createPickup(
      contentType = """application/json""",
      authorization = prefs.getAccessToken(),
      createPickupRequest = CreatePickupRequest(
      orderId = prefs.getOrderId())
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreatePickupResponse(response: CreatePickupResponse): Unit {
    val pickorderdetailModelValue = pickorderdetailModel.value ?:PickorderdetailModel()
    prefs.setMessage(response.payload?.message)
    prefs.setDeliveryKg(response.payload?.deliveryKg)
    pickorderdetailModel.value = pickorderdetailModelValue
  }
}
