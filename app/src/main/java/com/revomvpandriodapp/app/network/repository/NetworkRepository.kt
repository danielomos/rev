package com.revomvpandriodapp.app.network.repository

import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import com.revomvpandriodapp.app.extensions.NoInternetConnection
import com.revomvpandriodapp.app.extensions.isOnline
import com.revomvpandriodapp.app.network.RetrofitServices
import com.revomvpandriodapp.app.network.models.createemailsignup.CreateEmailSignupRequest
import com.revomvpandriodapp.app.network.models.createemailsignup.CreateEmailSignupResponse
import com.revomvpandriodapp.app.network.models.createemailsignup1.CreateEmailSignup1Request
import com.revomvpandriodapp.app.network.models.createemailsignup1.CreateEmailSignup1Response
import com.revomvpandriodapp.app.network.models.createmobiletokenverification.CreateMobileTokenVerificationRequest
import com.revomvpandriodapp.app.network.models.createmobiletokenverification.CreateMobileTokenVerificationResponse
import com.revomvpandriodapp.app.network.models.createphonesignup.CreatePhoneSignupRequest
import com.revomvpandriodapp.app.network.models.createphonesignup.CreatePhoneSignupResponse
import com.revomvpandriodapp.app.network.models.createphonesignup1.CreatePhoneSignup1Request
import com.revomvpandriodapp.app.network.models.createphonesignup1.CreatePhoneSignup1Response
import com.revomvpandriodapp.app.network.models.createrefill.CreateRefillRequest
import com.revomvpandriodapp.app.network.models.createrefill.CreateRefillResponse
import com.revomvpandriodapp.app.network.models.createsetup.CreateSetupRequest
import com.revomvpandriodapp.app.network.models.createsetup.CreateSetupResponse
import com.revomvpandriodapp.app.network.models.createtoken.CreateTokenRequest
import com.revomvpandriodapp.app.network.models.createtoken.CreateTokenResponse
import com.revomvpandriodapp.app.network.models.createtotal.CreateTotalRequest
import com.revomvpandriodapp.app.network.models.createtotal.CreateTotalResponse
import com.revomvpandriodapp.app.network.models.createupdate.CreateUpdateRequest
import com.revomvpandriodapp.app.network.models.createupdate.CreateUpdateResponse
import com.revomvpandriodapp.app.network.models.fetchareas.FetchAreasResponse
import com.revomvpandriodapp.app.network.models.fetchdetail.FetchDetailResponse
import com.revomvpandriodapp.app.network.models.fetchdetails.FetchDetailsResponse
import com.revomvpandriodapp.app.network.models.fetchdetails1.FetchDetails1Response
import com.revomvpandriodapp.app.network.models.fetchdistributors.FetchDistributorsResponse
import com.revomvpandriodapp.app.network.models.fetchid.FetchIdResponse
import com.revomvpandriodapp.app.network.models.fetchid1.FetchId1Response
import com.revomvpandriodapp.app.network.models.fetchtype.FetchTypeResponse
import com.revomvpandriodapp.app.network.resources.ErrorResponse
import com.revomvpandriodapp.app.network.resources.Response
import com.revomvpandriodapp.app.network.resources.SuccessResponse
import java.lang.Exception
import kotlin.String
import org.koin.core.KoinComponent
import org.koin.core.inject

class NetworkRepository : KoinComponent {
  private val retrofitServices: RetrofitServices by inject()

  private val errorMessage: String = "Something went wrong."

  suspend fun fetchId(
    contentType: String?,
    authorization: String?,
    id: String?
  ): Response<FetchIdResponse> = try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.fetchId(contentType, authorization, id))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun fetchId1(
    contentType: String?,
    authorization: String?,
    id: String?
  ): Response<FetchId1Response> = try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.fetchId1(contentType, authorization, id))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun fetchDetails(contentType: String?, authorization: String?):
      Response<FetchDetailsResponse> = try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.fetchDetails(contentType, authorization))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun fetchDistributors(
    contentType: String?,
    authorization: String?,
    serviceArea: String?
  ): Response<FetchDistributorsResponse> = try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.fetchDistributors(contentType, authorization, serviceArea))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun fetchDetail(
    contentType: String?,
    authorization: String?,
    sellerId: String?
  ): Response<FetchDetailResponse> = try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.fetchDetail(contentType, authorization, sellerId))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun createTotal(
    contentType: String?,
    authorization: String?,
    createTotalRequest: CreateTotalRequest?
  ): Response<CreateTotalResponse> = try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.createTotal(contentType, authorization, createTotalRequest))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun createRefill(
    contentType: String?,
    authorization: String?,
    createRefillRequest: CreateRefillRequest?
  ): Response<CreateRefillResponse> = try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.createRefill(contentType, authorization,
          createRefillRequest))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun createSetup(
    contentType: String?,
    authorization: String?,
    createSetupRequest: CreateSetupRequest?
  ): Response<CreateSetupResponse> = try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.createSetup(contentType, authorization, createSetupRequest))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun fetchAreas(): Response<FetchAreasResponse> = try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.fetchAreas())
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun createUpdate(
    contentType: String?,
    authorization: String?,
    createUpdateRequest: CreateUpdateRequest?
  ): Response<CreateUpdateResponse> = try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.createUpdate(contentType, authorization,
          createUpdateRequest))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun fetchDetails1(contentType: String?, authorization: String?):
      Response<FetchDetails1Response> = try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.fetchDetails1(contentType, authorization))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun fetchType(contentType: String?, authorization: String?): Response<FetchTypeResponse> =
      try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.fetchType(contentType, authorization))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun createToken(contentType: String?, createTokenRequest: CreateTokenRequest?):
      Response<CreateTokenResponse> = try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.createToken(contentType, createTokenRequest))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun createMobileTokenVerification(contentType: String?,
      createMobileTokenVerificationRequest: CreateMobileTokenVerificationRequest?):
      Response<CreateMobileTokenVerificationResponse> = try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.createMobileTokenVerification(contentType,
          createMobileTokenVerificationRequest))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun createPhoneSignup(contentType: String?,
      createPhoneSignupRequest: CreatePhoneSignupRequest?): Response<CreatePhoneSignupResponse> =
      try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.createPhoneSignup(contentType, createPhoneSignupRequest))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun createEmailSignup(contentType: String?,
      createEmailSignupRequest: CreateEmailSignupRequest?): Response<CreateEmailSignupResponse> =
      try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.createEmailSignup(contentType, createEmailSignupRequest))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun createPhoneSignup1(contentType: String?,
      createPhoneSignup1Request: CreatePhoneSignup1Request?): Response<CreatePhoneSignup1Response> =
      try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.createPhoneSignup1(contentType, createPhoneSignup1Request))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }

  suspend fun createEmailSignup1(contentType: String?,
      createEmailSignup1Request: CreateEmailSignup1Request?): Response<CreateEmailSignup1Response> =
      try {
    val isOnline = MyApp.getInstance().isOnline()
    if(isOnline) {
      SuccessResponse(retrofitServices.createEmailSignup1(contentType, createEmailSignup1Request))
    } else {
      val internetException =
          NoInternetConnection(MyApp.getInstance().getString(R.string.no_internet_connection))
      ErrorResponse(internetException.message ?:errorMessage, internetException)
    }
  } catch(e:Exception) {
    e.printStackTrace()
    ErrorResponse(e.message ?:errorMessage, e)
  }
}
