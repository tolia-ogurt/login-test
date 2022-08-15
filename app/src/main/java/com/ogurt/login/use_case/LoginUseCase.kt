package com.ogurt.login.use_case

import com.ogurt.login.model.LoginModel
import com.ogurt.login.model.ResponseModel
import com.ogurt.login.repo.LoginRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(loginModel: LoginModel): ResponseModel {
        return loginRepository.getLogin(loginModel)
    }
}