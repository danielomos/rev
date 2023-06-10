package com.revomvpandriodapp.app.modules.dashboard4gasmantwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.dashboard4gasmantwo.`data`.model.Dashboard4gasmantwoModel
import com.revomvpandriodapp.app.modules.dashboard4gasmantwo.`data`.model.Gasmanorder1RowModel
import com.revomvpandriodapp.app.network.models.fetchdetails.FetchDetailsResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class Dashboard4gasmantwoVM : ViewModel(), KoinComponent {
  val dashboard4gasmantwoModel: MutableLiveData<Dashboard4gasmantwoModel> =
      MutableLiveData(Dashboard4gasmantwoModel())


  var navArguments: Bundle? = null


  val gasmanorderList: MutableLiveData<MutableList<Gasmanorder1RowModel>> =
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
    val dashboard4gasmantwoModelValue = dashboard4gasmantwoModel.value ?:Dashboard4gasmantwoModel()
    val recyclerGasmanorder = response.payload?.sellerOrders?.map {
      Gasmanorder1RowModel(
      txt12KgTextFormFiOne = it?.orderAmount.toString(),
      txtPendingTextFor = it?.orderStatus.toString(),
      txtWeight = it?.orderKg.toString(),
      txtDate = it?.orderCreatedDate.toString(),
      )
      }?.toMutableList()
      gasmanorderList.value = recyclerGasmanorder
      dashboard4gasmantwoModelValue.txtHouseHoldTextFOne = response.payload?.name.toString()
      dashboard4gasmantwoModelValue.txtRV100001 = response.payload?.serialNumber.toString()
      dashboard4gasmantwoModelValue.txtZero = response.payload?.stockLevel.toString()
      dashboard4gasmantwoModelValue.txt1200Kg = response.payload?.sellingPrice.toString()
      prefs.setOnBoarded(response.payload?.onBoarded)
      dashboard4gasmantwoModel.value = dashboard4gasmantwoModelValue
    }
  }
