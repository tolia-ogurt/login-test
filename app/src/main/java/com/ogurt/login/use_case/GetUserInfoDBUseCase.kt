package com.ogurt.login.use_case

import com.ogurt.login.model.ResponseModel
import com.ogurt.login.repo.LoginRepository
import com.ogurt.login.utils.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserInfoDBUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {

    suspend operator fun invoke(): Flow<Resource<ResponseModel>> = flow {
        loginRepository.getUserInfo().collect { result ->
            when (result) {
                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
                is Resource.Success -> {
                    emit(Resource.Success(result.data))
                }
                is Resource.Error -> {
                    emit(Resource.Error(result.message ?: 0))
                }
            }
        }
    }
}