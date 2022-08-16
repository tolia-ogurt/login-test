package com.ogurt.login.utils

import android.text.TextUtils
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber
import java.io.Serializable

/**
 * This class is intended to represent input handling for the phone number.
 * It allows to format and transform the number
 */
class TelephoneNumber(number: String, country: String = "UA") : Serializable {
    var internationalNumber: String = number
    var userFriendlyLocalNumber: String? = null
    var isValid = false
    var numberCountry: String? = country

    val unformattedInternationalNumber: String
        get() = internationalNumber.filter { it != ' ' }

    init {
        if (TextUtils.isEmpty(number)) {
            isValid = false
        } else {
            var util: PhoneNumberUtil? = null
            var proto: PhoneNumber? = null
            var countryCode = ""
            try {
                util = PhoneNumberUtil.getInstance()
                proto = util.parse(number, country)
                countryCode = proto?.countryCode?.toString().orEmpty()
            } catch (e: Throwable) {
                isValid = false
            }
            if (null != util && null != proto) {
                // Next - parse it with libphonenumber
                try {
                    isValid = util.isValidNumber(proto)
                    if (!isValid && number.length >= 10 || number.startsWith(countryCode)) {
                        proto = util.parse("+$number", country)
                        isValid = util.isValidNumber(proto)
                    }
                    if (isValid) {
                        internationalNumber =
                            util.format(proto, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
                        val region = util.getRegionCodeForNumber(proto)
                        if (!TextUtils.isEmpty(region)) numberCountry = region
                        userFriendlyLocalNumber =
                            util.format(proto, PhoneNumberUtil.PhoneNumberFormat.NATIONAL)
                    }
                } catch (e: Throwable) {
                    isValid = false
                }
            }
        }
    }
}