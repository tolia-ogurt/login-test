package com.ogurt.login.model

fun LoginModel.toDomainModel() = LoginDomainModel(
    phoneCode = phoneCode,
    phoneNumber = phoneNumber,
    password = password
)


fun ResponseDomainModel.toResponseModel() = ResponseModel(
    name = user.name,
    surName = user.second_name,
    phoneNumber = user.phone_number
)
