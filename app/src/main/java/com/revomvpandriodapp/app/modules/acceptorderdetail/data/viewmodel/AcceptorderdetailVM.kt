package com.revomvpandriodapp.app.modules.acceptorderdetail.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.acceptorderdetail.`data`.model.AcceptorderdetailModel
import com.revomvpandriodapp.app.network.models.createaccept.CreateAcceptRequest
import com.revomvpandriodapp.app.network.models.createaccept.CreateAcceptResponse
import com.revomvpandriodapp.app.network.models.fetchid.FetchIdResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.Unit
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class AcceptorderdetailVM : ViewModel(), KoinComponent {
  val acceptorderdetailModel: MutableLiveData<AcceptorderdetailModel> =
      MutableLiveData(AcceptorderdetailModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val createAcceptLiveData: MutableLiveData<Response<CreateAcceptResponse>> =
      MutableLiveData<Response<CreateAcceptResponse>>()


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
    val acceptorderdetailModelValue = acceptorderdetailModel.value ?:AcceptorderdetailModel()
    prefs.setOrderId(response.payload?.orderId)
    acceptorderdetailModelValue.txt1010101010101 = response.payload?.orderCode.toString()
    acceptorderdetailModelValue.txtHibroWhats = response.payload?.orderAmount.toString()
    acceptorderdetailModelValue.txtWhatdoyouthi = response.payload?.orderKg.toString()
    acceptorderdetailModelValue.txtSendisachatThree = response.payload?.pickUpKg.toString()
    acceptorderdetailModelValue.txtSendisachatFour = response.payload?.deliveryKg.toString()
    acceptorderdetailModelValue.txtSendisachatTwo = response.payload?.orderCreatedDate.toString()
    acceptorderdetailModelValue.txtSendisachatFive = response.payload?.orderStatus.toString()
    acceptorderdetailModelValue.txtSendisachat = response.payload?.orderCustomerAddress.toString()
    acceptorderdetailModelValue.txtSendisachatOne = response.payload?.orderCusPhoneNumber.toString()
    acceptorderdetailModelValue.txtInteresttopar = response.payload?.orderCusFullName.toString()
    acceptorderdetailModel.value = acceptorderdetailModelValue
  }

  fun callCreateAcceptApi(): Unit {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createAcceptLiveData.postValue(
      networkRepository.createAccept(
      contentType = """application/json""",
      authorization = prefs.getAccessToken(),
      createAcceptRequest = CreateAcceptRequest(
      orderId = prefs.getOrderId())
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreateAcceptResponse(response: CreateAcceptResponse): Unit {
    val acceptorderdetailModelValue = acceptorderdetailModel.value ?:AcceptorderdetailModel()
    prefs.setMessage(response.payload?.message)
    acceptorderdetailModel.value = acceptorderdetailModelValue
  }
}
