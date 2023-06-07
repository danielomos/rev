package com.revomvpandriodapp.app.modules.gasmancontiner.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.gasmancontiner.`data`.model.GasmancontinerModel
import com.revomvpandriodapp.app.modules.gasmancontiner.`data`.model.GasmanorderRowModel
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
      txtWeight = it?.orderKg.toString(),
      txt12KgTextFormFiOne = it?.orderAmount.toString(),
      txtFrame247 = it?.orderCreatedDate.toString(),
      )
      }?.toMutableList()
      gasmanorderList.value = recyclerGasmanorder
      gasmancontinerModelValue.txtRevogasTextFor = response.payload?.name.toString()
      prefs.setOnBoarded(response.payload?.onBoarded)
      prefs.setUserType(response.payload?.userType)
      gasmancontinerModelValue.txtSeriaIdTextFor = response.payload?.serialNumber.toString()
      gasmancontinerModelValue.txtZero = response.payload?.sellingPrice.toString()
      gasmancontinerModelValue.txtZeroOne = response.payload?.stockLevel.toString()
      gasmancontinerModel.value = gasmancontinerModelValue
    }
  }
