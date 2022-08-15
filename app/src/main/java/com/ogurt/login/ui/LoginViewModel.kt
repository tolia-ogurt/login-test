package com.ogurt.login.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ogurt.login.model.LoginModel
import com.ogurt.login.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException

import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.HttpException

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    fun login() {
        viewModelScope.launch {
            try {
                val a = loginUseCase(LoginModel("+380", "671112233", "qwerty"))
                Log.d("PROVERKA", a.toString())

            } catch (http: HttpException) {
                Log.d("PROVERKA", http.toString())

            } catch (io: IOException) {
                Log.d("PROVERKA", io.toString())
            }
        }
    }
}