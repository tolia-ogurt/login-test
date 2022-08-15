package com.ogurt.login.model

import com.google.gson.annotations.SerializedName

data class LoginDomainModel(
    @SerializedName("phone_code") val phoneCode: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("password") val password: String,
)
