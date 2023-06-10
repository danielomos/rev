package com.revomvpandriodapp.app.network

import com.revomvpandriodapp.app.network.models.createaccept.CreateAcceptRequest
import com.revomvpandriodapp.app.network.models.createaccept.CreateAcceptResponse
import com.revomvpandriodapp.app.network.models.createcompleted.CreateCompletedRequest
import com.revomvpandriodapp.app.network.models.createcompleted.CreateCompletedResponse
import com.revomvpandriodapp.app.network.models.createdelivered.CreateDeliveredRequest
import com.revomvpandriodapp.app.network.models.createdelivered.CreateDeliveredResponse
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
import com.revomvpandriodapp.app.network.models.createpickup.CreatePickupRequest
import com.revomvpandriodapp.app.network.models.createpickup.CreatePickupResponse
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
import com.revomvpandriodapp.app.network.models.fetchall.FetchAllResponse
import com.revomvpandriodapp.app.network.models.fetchall1.FetchAll1Response
import com.revomvpandriodapp.app.network.models.fetchareas.FetchAreasResponse
import com.revomvpandriodapp.app.network.models.fetchdetail.FetchDetailResponse
import com.revomvpandriodapp.app.network.models.fetchdetails.FetchDetailsResponse
import com.revomvpandriodapp.app.network.models.fetchdetails1.FetchDetails1Response
import com.revomvpandriodapp.app.network.models.fetchdistributors.FetchDistributorsResponse
import com.revomvpandriodapp.app.network.models.fetchid.FetchIdResponse
import com.revomvpandriodapp.app.network.models.fetchid1.FetchId1Response
import com.revomvpandriodapp.app.network.models.fetchtype.FetchTypeResponse
import kotlin.String
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitServices {
  @POST("/api/customer/individual/order/completed")
  suspend fun createCompleted(
    @Header("Content-type") contentType: String?,
    @Header("Authorization") authorization: String?,
    @Body createCompletedRequest: CreateCompletedRequest?
  ): CreateCompletedResponse

  @POST("/api/distributor/retailer/delivered")
  suspend fun createDelivered(
    @Header("Content-type") contentType: String?,
    @Header("Authorization") authorization: String?,
    @Body createDeliveredRequest: CreateDeliveredRequest?
  ): CreateDeliveredResponse

  @POST("/api/distributor/retailer/pickup")
  suspend fun createPickup(
    @Header("Content-type") contentType: String?,
    @Header("Authorization") authorization: String?,
    @Body createPickupRequest: CreatePickupRequest?
  ): CreatePickupResponse

  @POST("/api/distributor/retailer/order/accept")
  suspend fun createAccept(
    @Header("Content-type") contentType: String?,
    @Header("Authorization") authorization: String?,
    @Body createAcceptRequest: CreateAcceptRequest?
  ): CreateAcceptResponse

  @GET("/api/distributor/retailer/orders/{id}/")
  suspend fun fetchId(
    @Header("Content-type") contentType: String?,
    @Header("Authorization") authorization: String?,
    @Query("id") id: String?
  ): FetchIdResponse

  @GET("/api/distributor/retailer/order/all")
  suspend fun fetchAll(@Header("Content-type") contentType: String?, @Header("Authorization")
      authorization: String?): FetchAllResponse

  @GET("/api/customer/individual/order/{id}/")
  suspend fun fetchId1(
    @Header("Content-type") contentType: String?,
    @Header("Authorization") authorization: String?,
    @Query("id") id: String?
  ): FetchId1Response

  @GET("/api/customer/individual/order/all")
  suspend fun fetchAll1(@Header("content-Type") contentType: String?, @Header("Authorization")
      authorization: String?): FetchAll1Response

  @GET("/api/distributor/retailer/profile/details")
  suspend fun fetchDetails(@Header("Content-type") contentType: String?, @Header("Authorization")
      authorization: String?): FetchDetailsResponse

  @GET("/api/customer/individual/distributors/")
  suspend fun fetchDistributors(
    @Header("Content-type") contentType: String?,
    @Header("Authorization") authorization: String?,
    @Query("serviceArea") serviceArea: String?
  ): FetchDistributorsResponse

  @GET("/api/customer/individual/distributor/detail/")
  suspend fun fetchDetail(
    @Header("Content-type") contentType: String?,
    @Header("Authorization") authorization: String?,
    @Query("sellerId") sellerId: String?
  ): FetchDetailResponse

  @POST("/api/customer/individual/orders/total")
  suspend fun createTotal(
    @Header("Content-type") contentType: String?,
    @Header("Authorization") authorization: String?,
    @Body createTotalRequest: CreateTotalRequest?
  ): CreateTotalResponse

  @POST("/api/customer/individual/gas/refill")
  suspend fun createRefill(
    @Header("Content-type") contentType: String?,
    @Header("Authorization") authorization: String?,
    @Body createRefillRequest: CreateRefillRequest?
  ): CreateRefillResponse

  @POST("/api/distributor/retailer/setup")
  suspend fun createSetup(
    @Header("content-Type") contentType: String?,
    @Header("Authorization") authorization: String?,
    @Body createSetupRequest: CreateSetupRequest?
  ): CreateSetupResponse

  @GET("/api/auth/service/areas")
  suspend fun fetchAreas(): FetchAreasResponse

  @POST("/api/distributor/retailer/stock/update")
  suspend fun createUpdate(
    @Header("Content-type") contentType: String?,
    @Header("Authorization") authorization: String?,
    @Body createUpdateRequest: CreateUpdateRequest?
  ): CreateUpdateResponse

  @GET("/api/customer/individual/profile/details")
  suspend fun fetchDetails1(@Header("Content-type") contentType: String?, @Header("Authorization")
      authorization: String?): FetchDetails1Response

  @GET("/api/auth/user/type")
  suspend fun fetchType(@Header("Content-type") contentType: String?, @Header("Authorization")
      authorization: String?): FetchTypeResponse

  @POST("/api/auth/token")
  suspend fun createToken(@Header("Content-type") contentType: String?, @Body
      createTokenRequest: CreateTokenRequest?): CreateTokenResponse

  @POST("/api/auth/mobileTokenVerification")
  suspend fun createMobileTokenVerification(@Header("Content-type") contentType: String?, @Body
      createMobileTokenVerificationRequest: CreateMobileTokenVerificationRequest?):
      CreateMobileTokenVerificationResponse

  @POST("/api/distributor/retailer/phone-signup")
  suspend fun createPhoneSignup(@Header("Content-type") contentType: String?, @Body
      createPhoneSignupRequest: CreatePhoneSignupRequest?): CreatePhoneSignupResponse

  @POST("/api/distributor/retailer/email-signup")
  suspend fun createEmailSignup(@Header("Content-type") contentType: String?, @Body
      createEmailSignupRequest: CreateEmailSignupRequest?): CreateEmailSignupResponse

  @POST("/api/customer/individual/phone-signup")
  suspend fun createPhoneSignup1(@Header("Content-type") contentType: String?, @Body
      createPhoneSignup1Request: CreatePhoneSignup1Request?): CreatePhoneSignup1Response

  @POST("/api/customer/individual/email-signup")
  suspend fun createEmailSignup1(@Header("Content-type") contentType: String?, @Body
      createEmailSignup1Request: CreateEmailSignup1Request?): CreateEmailSignup1Response
}

const val BASE_URL: String = "http://localhost:8668"
