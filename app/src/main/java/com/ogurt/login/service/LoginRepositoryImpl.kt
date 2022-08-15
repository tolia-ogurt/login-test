package com.ogurt.login.service

import com.ogurt.login.model.LoginModel
import com.ogurt.login.model.ResponseModel
import com.ogurt.login.model.toDomainModel
import com.ogurt.login.model.toResponseModel
import com.ogurt.login.repo.LoginRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
) : LoginRepository {

    override suspend fun getLogin(loginModel: LoginModel): ResponseModel =
        loginService.getLogin(loginModel.toDomainModel()).toResponseModel()
}