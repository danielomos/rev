package com.revomvpandriodapp.app.appcomponents.utility

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.revomvpandriodapp.app.R
import com.revomvpandriodapp.app.appcomponents.di.MyApp
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit

class PreferenceHelper {
  private val masterKeyAlias: String = createGetMasterKey()


  private val sharedPreference: SharedPreferences = EncryptedSharedPreferences.create(
  MyApp.getInstance().resources.getString(R.string.app_name),
  masterKeyAlias,
  MyApp.getInstance().applicationContext,
  EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
  EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
  )


  /**
   * Creates or gets the master key provided,
   * The encryption scheme is required fields to ensure that the type of encryption used is clear to
   * developers.
   *
   * @return the string value of encrypted key
   */
  private fun createGetMasterKey(): String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

  fun setMessage(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("message", paramValue!!)
      apply()
    }
  }

  fun getMessage(): String? = sharedPreference.getString("message", null)

  fun setAccessToken(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("accessToken", paramValue!!)
      apply()
    }
  }

  fun getAccessToken(): String? = sharedPreference.getString("accessToken", null)

  fun setOnBoarded(paramValue: Int?): Unit {
    with(sharedPreference.edit()) {
      this.putInt("onBoarded", paramValue!!)
      apply()
    }
  }

  fun getOnBoarded(): Int? = sharedPreference.getInt("onBoarded", 0)

  fun setDeliveryKg(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("deliveryKg", paramValue!!)
      apply()
    }
  }

  fun getDeliveryKg(): String? = sharedPreference.getString("deliveryKg", null)

  fun setOrderId(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("orderId", paramValue!!)
      apply()
    }
  }

  fun getOrderId(): String? = sharedPreference.getString("orderId", null)

  fun setSellerAddress(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("sellerAddress", paramValue!!)
      apply()
    }
  }

  fun getSellerAddress(): String? = sharedPreference.getString("sellerAddress", null)

  fun setPayload(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("payload", paramValue!!)
      apply()
    }
  }

  fun getPayload(): String? = sharedPreference.getString("payload", null)

  fun setType(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("type", paramValue!!)
      apply()
    }
  }

  fun getType(): String? = sharedPreference.getString("type", null)

  fun setAccExpiredAt(paramValue: Long?): Unit {
    with(sharedPreference.edit()) {
      this.putLong("accExpiredAt", paramValue!!)
      apply()
    }
  }

  fun getAccExpiredAt(): Long? = sharedPreference.getLong("accExpiredAt", 0L)

  fun setRefreshToken(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("refreshToken", paramValue!!)
      apply()
    }
  }

  fun getRefreshToken(): String? = sharedPreference.getString("refreshToken", null)

  fun setRefExpiredAt(paramValue: Long?): Unit {
    with(sharedPreference.edit()) {
      this.putLong("refExpiredAt", paramValue!!)
      apply()
    }
  }

  fun getRefExpiredAt(): Long? = sharedPreference.getLong("refExpiredAt", 0L)

  fun setUserType(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("userType", paramValue!!)
      apply()
    }
  }

  fun getUserType(): String? = sharedPreference.getString("userType", null)
}
