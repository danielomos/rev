package com.revomvpandriodapp.app.modules.householddashboardhome.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.householddashboardhome.`data`.model.CustomersorderRowModel
import com.revomvpandriodapp.app.modules.householddashboardhome.`data`.model.HouseholddashboardHomeModel
import com.revomvpandriodapp.app.network.models.fetchdetails1.FetchDetails1Response
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HouseholddashboardHomeVM : ViewModel(), KoinComponent {
  val householddashboardHomeModel: MutableLiveData<HouseholddashboardHomeModel> =
      MutableLiveData(HouseholddashboardHomeModel())


  var navArguments: Bundle? = null


  val customersorderList: MutableLiveData<MutableList<CustomersorderRowModel>> =
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
    val householddashboardHomeModelValue = householddashboardHomeModel.value
        ?:HouseholddashboardHomeModel()
    val recyclerCustomersorder = response.payload?.customerOrders?.map {
      CustomersorderRowModel(
      txt12KgTextFormFiOne = it?.orderAmount.toString(),
      txtPendingTextFor = it?.orderStatus.toString(),
      txtWeight = it?.orderKg.toString(),
      txtFrame247 = it?.orderCreatedDate.toString(),
      )
      }?.toMutableList()
      customersorderList.value = recyclerCustomersorder
      householddashboardHomeModelValue.txtHouseHoldTextFOne = response.payload?.name.toString()
      prefs.setOnBoarded(response.payload?.onBoarded)
      prefs.setUserType(response.payload?.userType)
      householddashboardHomeModelValue.txtZeroOne = response.payload?.sellersPrice.toString()
      householddashboardHomeModelValue.txtBA1200000TextF = response.payload?.serialNumber.toString()
      householddashboardHomeModel.value = householddashboardHomeModelValue
    }
  }
