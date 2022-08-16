package com.ogurt.login.repo

import com.ogurt.login.R
import com.ogurt.login.model.LoginModel
import com.ogurt.login.model.ResponseModel
import com.ogurt.login.model.toDomainModel
import com.ogurt.login.model.toResponseModel
import com.ogurt.login.model.toUserInfoEntity
import com.ogurt.login.service.LoginService
import com.ogurt.login.utils.Resource
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import room.UserInfoDao

@Singleton
class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
    private val dao: UserInfoDao,
) : LoginRepository {

    override suspend fun getLogin(loginModel: LoginModel): Flow<Resource<ResponseModel>> = flow {
        emit(Resource.Loading())
        try {
            val response = loginService.getLogin(loginModel.toDomainModel())
            emit(Resource.Success(response.toResponseModel()))
        } catch (http: HttpException) {
            if (http.code() == 401) {
                emit(Resource.Error(message = (R.string.http_exception_login)))
            } else {
                emit(Resource.Error(message = (R.string.exception)))
            }
        } catch (io: IOException) {
            emit(Resource.Error(message = (R.string.error_internet_connection)))
        }
    }

    override suspend fun addUserInfo(responseModel: ResponseModel) {
        dao.addUserInfo(responseModel.toUserInfoEntity())
    }

    override suspend fun getUserInfo(): Flow<Resource<ResponseModel>> = flow {
        emit(Resource.Loading())
        try {
            val response = dao.getUserInfo().toResponseModel()
            emit(Resource.Success(response))
        } catch (io: IOException) {
            emit(Resource.Error(message = (R.string.exception)))
        }
    }
}