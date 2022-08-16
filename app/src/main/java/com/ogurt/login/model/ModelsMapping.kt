package com.ogurt.login.model

import room.UserInfoEntity

fun LoginModel.toDomainModel() = LoginDomainModel(
    phoneCode = phoneCode,
    phoneNumber = phoneNumber,
    password = password
)


fun ResponseDomainModel.toResponseModel() = ResponseModel(
    name = user.name,
    surName = user.second_name,
    phoneNumber = user.phone_number,
    phoneCode = user.phone_code
)

fun ResponseModel.toUserInfoEntity() = UserInfoEntity(
    name = name,
    surName = surName,
    phoneNumber = phoneNumber,
    phoneCode = phoneCode
)

fun UserInfoEntity.toResponseModel() = ResponseModel(
    name = name,
    surName = surName,
    phoneNumber = phoneNumber,
    phoneCode = phoneCode
)
