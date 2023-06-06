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

  fun setIndividualId(paramValue: Int?): Unit {
    with(sharedPreference.edit()) {
      this.putInt("individualId", paramValue!!)
      apply()
    }
  }

  fun getIndividualId(): Int? = sharedPreference.getInt("individualId", 0)

  fun setEmail(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("email", paramValue!!)
      apply()
    }
  }

  fun getEmail(): String? = sharedPreference.getString("email", null)

  fun setFamilyName(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("familyName", paramValue!!)
      apply()
    }
  }

  fun getFamilyName(): String? = sharedPreference.getString("familyName", null)

  fun setGivenName(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("givenName", paramValue!!)
      apply()
    }
  }

  fun getGivenName(): String? = sharedPreference.getString("givenName", null)

  fun setPhoneNumber(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("phoneNumber", paramValue!!)
      apply()
    }
  }

  fun getPhoneNumber(): String? = sharedPreference.getString("phoneNumber", null)

  fun setGender(paramValue: Int?): Unit {
    with(sharedPreference.edit()) {
      this.putInt("gender", paramValue!!)
      apply()
    }
  }

  fun getGender(): Int? = sharedPreference.getInt("gender", 0)

  fun setManage(paramValue: Int?): Unit {
    with(sharedPreference.edit()) {
      this.putInt("manage", paramValue!!)
      apply()
    }
  }

  fun getManage(): Int? = sharedPreference.getInt("manage", 0)

  fun setUserType(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("userType", paramValue!!)
      apply()
    }
  }

  fun getUserType(): String? = sharedPreference.getString("userType", null)

  fun setOnBoarded(paramValue: Int?): Unit {
    with(sharedPreference.edit()) {
      this.putInt("onBoarded", paramValue!!)
      apply()
    }
  }

  fun getOnBoarded(): Int? = sharedPreference.getInt("onBoarded", 0)

  fun setAddress(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("address", paramValue!!)
      apply()
    }
  }

  fun getAddress(): String? = sharedPreference.getString("address", null)

  fun setDefaultProfileId(paramValue: Int?): Unit {
    with(sharedPreference.edit()) {
      this.putInt("defaultProfileId", paramValue!!)
      apply()
    }
  }

  fun getDefaultProfileId(): Int? = sharedPreference.getInt("defaultProfileId", 0)

  fun setRetailerId(paramValue: Int?): Unit {
    with(sharedPreference.edit()) {
      this.putInt("retailerId", paramValue!!)
      apply()
    }
  }

  fun getRetailerId(): Int? = sharedPreference.getInt("retailerId", 0)

  fun setServiceArea(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("serviceArea", paramValue!!)
      apply()
    }
  }

  fun getServiceArea(): String? = sharedPreference.getString("serviceArea", null)

  fun setSellerAddress(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("sellerAddress", paramValue!!)
      apply()
    }
  }

  fun getSellerAddress(): String? = sharedPreference.getString("sellerAddress", null)

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

  fun setPayload(paramValue: String?): Unit {
    with(sharedPreference.edit()) {
      this.putString("payload", paramValue!!)
      apply()
    }
  }

  fun getPayload(): String? = sharedPreference.getString("payload", null)
}
