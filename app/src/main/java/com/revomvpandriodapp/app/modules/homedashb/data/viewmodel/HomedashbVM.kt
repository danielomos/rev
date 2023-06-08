package com.revomvpandriodapp.app.modules.homedashb.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.homedashb.`data`.model.Gasmanorder1RowModel
import com.revomvpandriodapp.app.modules.homedashb.`data`.model.HomedashbModel
import com.revomvpandriodapp.app.network.models.fetchdetails1.FetchDetails1Response
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomedashbVM : ViewModel(), KoinComponent {
  val homedashbModel: MutableLiveData<HomedashbModel> = MutableLiveData(HomedashbModel())


  var navArguments: Bundle? = null


  val gasmanorderList: MutableLiveData<MutableList<Gasmanorder1RowModel>> =
      MutableLiveData(mutableListOf())


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val fetchDetails1LiveData: MutableLiveData<Response<FetchDetails1Response>> =
      MutableLiveData<Response<FetchDetails1Response>>()

  fun callFetchDetails1Api() {
    viewModelScope.launch {
      progressLiveData.postValue(true)
      fetchDetails1LiveData.postValue(
      networkRepository.fetchDetails1(
      contentType = """application/json""",
          authorization = prefs.getAccessToken()
      )
      )
      progressLiveData.postValue(false)
    }
  }

  fun bindFetchDetails1Response(response: FetchDetails1Response) {
    val homedashbModelValue = homedashbModel.value ?:HomedashbModel()
    val recyclerGasmanorder = response.payload?.customerOrders?.map {
      Gasmanorder1RowModel(
      txtPendingTextFor = it?.orderStatus.toString(),
      txtWeight = it?.orderKg.toString(),
      txt12KgTextFormFiOne = it?.orderAmount.toString(),
      txtFrame247 = it?.orderCreatedDate.toString(),
      )
      }?.toMutableList()
      gasmanorderList.value = recyclerGasmanorder
      homedashbModelValue.txtHouseHoldTextF = response.payload?.name.toString()
      prefs.setOnBoarded(response.payload?.onBoarded)
      homedashbModelValue.txtRV100001TextF = response.payload?.serialNumber.toString()
      homedashbModelValue.txt1200Kg = response.payload?.sellersPrice.toString()
      homedashbModel.value = homedashbModelValue
    }
  }
