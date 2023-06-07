package com.revomvpandriodapp.app.modules.cusorderpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revomvpandriodapp.app.appcomponents.utility.PreferenceHelper
import com.revomvpandriodapp.app.modules.cusorderpage.`data`.model.CusorderpageModel
import com.revomvpandriodapp.app.modules.cusorderpage.`data`.model.CusorderpageRowModel
import com.revomvpandriodapp.app.network.models.fetchid1.FetchId1Response
import com.revomvpandriodapp.app.network.repository.NetworkRepository
import com.revomvpandriodapp.app.network.resources.Response
import kotlin.Boolean
import kotlin.collections.MutableList
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class CusorderpageVM : ViewModel(), KoinComponent {
  val cusorderpageModel: MutableLiveData<CusorderpageModel> = MutableLiveData(CusorderpageModel())


  var navArguments: Bundle? = null


  val cusorderpageList: MutableLiveData<MutableList<CusorderpageRowModel>> =
      MutableLiveData(mutableListOf())


  val progressLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

  private val networkRepository: NetworkRepository by inject()

  private val prefs: PreferenceHelper by inject()

  val fetchId1LiveData: MutableLiveData<Response<FetchId1Response>> =
      MutableLiveData<Response<FetchId1Response>>()

  fun callFetchId1Api() {
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

  fun bindFetchId1Response(response: FetchId1Response) {
    val cusorderpageModelValue = cusorderpageModel.value ?:CusorderpageModel()
    cusorderpageModel.value = cusorderpageModelValue
  }
}
