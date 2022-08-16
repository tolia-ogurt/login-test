package com.ogurt.login.use_case

import com.ogurt.login.model.ResponseModel
import com.ogurt.login.repo.LoginRepository
import javax.inject.Inject

class AddUserInfoDBUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {

    suspend operator fun invoke(responseModel: ResponseModel) {
        loginRepository.addUserInfo(responseModel)
    }
}