package com.revomvpandriodapp.app.modules.gasmanorderpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.gasmanorderpage.`data`.model.GasmanorderpageModel
import com.revomvpandriodapp.app.modules.gasmanorderpage.`data`.model.ListordercodeRowModel
import com.revomvpandriodapp.app.network.models.fetchid.FetchIdResponse
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class GasmanorderpageVM : ViewModel(), KoinComponent {
  val gasmanorderpageModel: MutableLiveData<GasmanorderpageModel> =
      MutableLiveData(GasmanorderpageModel())


  var navArguments: Bundle? = null


  val listordercodeList: MutableLiveData<MutableList<ListordercodeRowModel>> =
      MutableLiveData(mutableListOf())


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val fetchIdLiveData: MutableLiveData<Response<FetchIdResponse>> =
      MutableLiveData<Response<FetchIdResponse>>()

  fun callFetchIdApi() {
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

  fun bindFetchIdResponse(response: FetchIdResponse) {
    val gasmanorderpageModelValue = gasmanorderpageModel.value ?:GasmanorderpageModel()
    gasmanorderpageModel.value = gasmanorderpageModelValue
  }
}
