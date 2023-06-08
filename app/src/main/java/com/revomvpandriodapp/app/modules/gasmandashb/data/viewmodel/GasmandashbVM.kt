package com.revomvpandriodapp.app.modules.gasmandashb.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.gasmandashb.`data`.model.GasmandashbModel
import com.revomvpandriodapp.app.modules.gasmandashb.`data`.model.GasmanorderRowModel
import com.revomvpandriodapp.app.modules.gasmandashb.`data`.model.ListcalculatorRowModel
import com.revomvpandriodapp.app.network.models.fetchdetails.FetchDetailsResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class GasmandashbVM : ViewModel(), KoinComponent {
  val gasmandashbModel: MutableLiveData<GasmandashbModel> = MutableLiveData(GasmandashbModel())


  var navArguments: Bundle? = null


  val listcalculatorList: MutableLiveData<MutableList<ListcalculatorRowModel>> =
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
    val gasmandashbModelValue = gasmandashbModel.value ?:GasmandashbModel()
    val recyclerGasmanorder = response.payload?.sellerOrders?.map {
      GasmanorderRowModel(
      txtPendingTextFor = it?.orderStatus.toString(),
      txt12KgTextFormFiOne = it?.orderAmount.toString(),
      txtWeight = it?.orderKg.toString(),
      txtFrame247 = it?.orderCreatedDate.toString(),
      )
      }?.toMutableList()
      gasmanorderList.value = recyclerGasmanorder
      gasmandashbModelValue.txtHouseHoldTextF = response.payload?.name.toString()
      gasmandashbModelValue.txtZero = response.payload?.stockLevel.toString()
      gasmandashbModelValue.txt1200Kg = response.payload?.sellingPrice.toString()
      gasmandashbModelValue.txtRV100001TextF = response.payload?.serialNumber.toString()
      prefs.setOnBoarded(response.payload?.onBoarded)
      gasmandashbModel.value = gasmandashbModelValue
    }
  }
