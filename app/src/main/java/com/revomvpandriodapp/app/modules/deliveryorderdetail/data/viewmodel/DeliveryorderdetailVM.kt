package com.revomvpandriodapp.app.modules.deliveryorderdetail.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revomvpandriodapp.app.modules.deliveryorderdetail.`data`.model.DeliveryorderdetailModel
import org.koin.core.KoinComponent

class DeliveryorderdetailVM : ViewModel(), KoinComponent {
  val deliveryorderdetailModel: MutableLiveData<DeliveryorderdetailModel> =
      MutableLiveData(DeliveryorderdetailModel())

  var navArguments: Bundle? = null
}
