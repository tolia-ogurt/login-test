package com.ogurt.login.repo

import com.ogurt.login.model.LoginModel
import com.ogurt.login.model.ResponseModel

interface LoginRepository {
    suspend fun getLogin(loginModel: LoginModel): ResponseModel
}