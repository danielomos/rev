package com.revomvpandriodapp.app.modules.gasmancontiner.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.gasmancontiner.`data`.model.GasmancontinerModel
import com.revomvpandriodapp.app.modules.gasmancontiner.`data`.model.GasmanorderRowModel
import com.revomvpandriodapp.app.modules.gasmancontiner.`data`.model.ListsellingPriceRowModel
import com.revomvpandriodapp.app.network.models.fetchdetails.FetchDetailsResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class GasmancontinerVM : ViewModel(), KoinComponent {
  val gasmancontinerModel: MutableLiveData<GasmancontinerModel> =
      MutableLiveData(GasmancontinerModel())


  var navArguments: Bundle? = null


  val listsellingPriceList: MutableLiveData<MutableList<ListsellingPriceRowModel>> =
      MutableLiveData(mutableListOf())


  val gasmanorderList: MutableLiveData<MutableList<GasmanorderRowModel>> =
      MutableLiveData(mutableListOf())


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val fetchDetailsLiveData: MutableLiveData<Response<FetchDetailsResponse>> =
      MutableLiveData<Response<FetchDetailsResponse>>()

  fun callFetchDetailsApi() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      fetchDetailsLiveData.postValue(
      networkRepository.fetchDetails(
      contentType = """application/json""",
          authorization = prefs.getAccessToken()
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindFetchDetailsResponse(response: FetchDetailsResponse) {
    val gasmancontinerModelValue = gasmancontinerModel.value ?:GasmancontinerModel()
    val recyclerGasmanorder = response.payload?.sellerOrders?.map {
      GasmanorderRowModel(
      txtPendingTextFor = it?.orderStatus.toString(),
      txt12KgTextFormFiOne = it?.orderAmount.toString(),
      txtWeight = it?.orderKg.toString(),
      txt10042023Text = it?.orderCreatedDate.toString(),
      )
      }?.toMutableList()
      gasmanorderList.value = recyclerGasmanorder
      prefs.setRetailerId(response.payload?.retailerId)
      prefs.setEmail(response.payload?.email)
      prefs.setFamilyName(response.payload?.familyName)
      prefs.setGivenName(response.payload?.givenName)
      gasmancontinerModelValue.txtRevogasTextFor = response.payload?.name.toString()
      prefs.setPhoneNumber(response.payload?.phoneNumber)
      prefs.setGender(response.payload?.gender)
      prefs.setOnBoarded(response.payload?.onBoarded)
      prefs.setDefaultProfileId(response.payload?.defaultProfileId)
      prefs.setUserType(response.payload?.userType)
      gasmancontinerModelValue.txtSeriaIdTextFor = response.payload?.serialNumber.toString()
      prefs.setAddress(response.payload?.address)
      prefs.setServiceArea(response.payload?.serviceArea)
      gasmancontinerModel.value = gasmancontinerModelValue
    }
  }
