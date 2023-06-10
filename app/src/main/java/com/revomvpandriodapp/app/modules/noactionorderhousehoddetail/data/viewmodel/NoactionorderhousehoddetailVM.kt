package com.revomvpandriodapp.app.modules.noactionorderhousehoddetail.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.noactionorderhousehoddetail.`data`.model.NoactionorderhousehoddetailModel
import com.revomvpandriodapp.app.network.models.createcompleted.CreateCompletedRequest
import com.revomvpandriodapp.app.network.models.createcompleted.CreateCompletedResponse
import com.revomvpandriodapp.app.network.models.fetchid1.FetchId1Response
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.Unit
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class NoactionorderhousehoddetailVM : ViewModel(), KoinComponent {
  val noactionorderhousehoddetailModel: MutableLiveData<NoactionorderhousehoddetailModel> =
      MutableLiveData(NoactionorderhousehoddetailModel())


  var navArguments: Bundle? = null


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val fetchId1LiveData: MutableLiveData<Response<FetchId1Response>> =
      MutableLiveData<Response<FetchId1Response>>()


  val createCompletedLiveData: MutableLiveData<Response<CreateCompletedResponse>> =
      MutableLiveData<Response<CreateCompletedResponse>>()

  fun callCreateCompletedApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      createCompletedLiveData.postValue(
      networkRepository.createCompleted(
      contentType = """application/json""",
          authorization = prefs.getAccessToken(),
          createCompletedRequest = CreateCompletedRequest(orderId =
              navArguments?.getString("cusOrderId"))
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindCreateCompletedResponse(response: CreateCompletedResponse) {
    val noactionorderhousehoddetailModelValue = noactionorderhousehoddetailModel.value
        ?:NoactionorderhousehoddetailModel()
    prefs.setPayload(response.payload)
    noactionorderhousehoddetailModel.value = noactionorderhousehoddetailModelValue
  }

  fun callFetchId1Api(): Unit {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      fetchId1LiveData.postValue(
      networkRepository.fetchId1(
      contentType = """application/json""",
      authorization = prefs.getAccessToken(),
      id = navArguments?.getString("cusOrderId")
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindFetchId1Response(response: FetchId1Response): Unit {
    val noactionorderhousehoddetailModelValue = noactionorderhousehoddetailModel.value
    ?:NoactionorderhousehoddetailModel()
    noactionorderhousehoddetailModelValue.txt1010101010101 = response.payload?.orderCode.toString()
    noactionorderhousehoddetailModelValue.txtHibroWhats = response.payload?.orderAmount.toString()
    noactionorderhousehoddetailModelValue.txtSendisachatThree =
    response.payload?.pickUpKg.toString()
    noactionorderhousehoddetailModelValue.txtWhatdoyouthi = response.payload?.orderKg.toString()
    noactionorderhousehoddetailModelValue.txtSendisachatFour =
    response.payload?.deliveryKg.toString()
    noactionorderhousehoddetailModelValue.txtSendisachatTwo =
    response.payload?.orderCreatedDate.toString()
    noactionorderhousehoddetailModelValue.txtInteresttopar =
    response.payload?.orderSellerShopName.toString()
    noactionorderhousehoddetailModelValue.txtSendisachatFive =
    response.payload?.orderStatus.toString()
    noactionorderhousehoddetailModelValue.txtSendisachat =
    response.payload?.orderSelAddress.toString()
    noactionorderhousehoddetailModelValue.txtSendisachatOne =
    response.payload?.orderSelPhoneNumber.toString()
    noactionorderhousehoddetailModelValue.txtInteresttoparOne =
    response.payload?.orderSelFullName.toString()
    noactionorderhousehoddetailModel.value = noactionorderhousehoddetailModelValue
  }
}
