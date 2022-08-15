package com.ogurt.login.service

import com.ogurt.login.model.LoginDomainModel
import com.ogurt.login.model.ResponseDomainModel
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/login")
    suspend fun getLogin(@Body loginModel: LoginDomainModel): ResponseDomainModel
}