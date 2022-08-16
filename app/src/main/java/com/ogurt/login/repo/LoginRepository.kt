package com.ogurt.login.repo

import com.ogurt.login.model.LoginModel
import com.ogurt.login.model.ResponseModel
import com.ogurt.login.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun getLogin(loginModel: LoginModel): Flow<Resource<ResponseModel>>
    suspend fun addUserInfo(responseModel: ResponseModel)
    suspend fun getUserInfo(): Flow<Resource<ResponseModel>>
}