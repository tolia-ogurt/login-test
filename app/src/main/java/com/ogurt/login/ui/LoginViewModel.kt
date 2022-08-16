package com.ogurt.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ogurt.login.model.LoginModel
import com.ogurt.login.model.ResponseModel
import com.ogurt.login.use_case.AddUserInfoDBUseCase
import com.ogurt.login.use_case.GetUserInfoDBUseCase
import com.ogurt.login.use_case.LoginUseCase
import com.ogurt.login.utils.Resource
import com.ogurt.login.utils.SingleLiveEvent
import com.ogurt.login.utils.TelephoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val addUserInfoDBUseCase: AddUserInfoDBUseCase,
    private val getUserInfoDBUseCase: GetUserInfoDBUseCase,
) : ViewModel() {

    private val _userInfo = SingleLiveEvent<ResponseModel>()
    val userInfo: LiveData<ResponseModel> get() = _userInfo
    val error = SingleLiveEvent<Int>()
    val onInvalidPhoneNumber = SingleLiveEvent<Unit>()
    val isLoading = MutableLiveData(false)

    fun login(rawPhoneNumber: String, password: String) {
        val phoneNumber = TelephoneNumber(rawPhoneNumber)
        phoneNumber.numberCountry
        phoneNumber.unformattedInternationalNumber
        if (!phoneNumber.isValid) {
            onInvalidPhoneNumber.call()
        } else {
            viewModelScope.launch {
                val phoneNumberWithoutCode = rawPhoneNumber.removeRange(0, 4)
                val phoneCode = rawPhoneNumber.removeRange(4, rawPhoneNumber.length)
                loginUseCase(LoginModel(phoneCode, phoneNumberWithoutCode, password)).collect {
                    when (it) {
                        is Resource.Loading -> {
                            isLoading.value = true
                        }
                        is Resource.Success -> {
                            it.data?.let { model ->
                                _userInfo.call()
                                isLoading.value = false
                                addUserInfoToDB(model)
                            }
                        }
                        is Resource.Error -> {
                            error.value = it.message ?: 0
                            isLoading.value = false
                        }
                    }
                }
            }
        }
    }

    fun getUserInfoToDB() {
        viewModelScope.launch {
            getUserInfoDBUseCase().collect {
                when (it) {
                    is Resource.Loading -> {
                        isLoading.value = true
                    }
                    is Resource.Success -> {
                        it.data?.let { model ->
                            _userInfo.value = model
                        }
                        isLoading.value = false
                    }
                    is Resource.Error -> {
                        error.value = it.message ?: 0
                        isLoading.value = false
                    }
                }
            }
        }
    }

    private fun addUserInfoToDB(responseModel: ResponseModel) {
        viewModelScope.launch { addUserInfoDBUseCase(responseModel) }
    }
}