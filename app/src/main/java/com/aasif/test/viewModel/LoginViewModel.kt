package com.aasif.test.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aasif.test.MyRepository
import com.aasif.test.data.FoodsCate
import com.aasif.test.data.Login
import com.aasif.test.data.Register
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {


    fun login(email: String, password: String, success: (String) -> Unit, fail: (String) -> Unit) {

        viewModelScope.launch {

            try {
                val response = repository.login(
                    Login(email, password, "0", "TOKEN12345")
                )

                if (response.statusCode == 200) success(response.message)
                else fail(response.message)

            } catch (e: Exception) {
                fail("Error: ${e.localizedMessage}")
            }
        }

    }

    fun signup(register: Register, success: (String) -> Unit, fail: (String) -> Unit) {

        viewModelScope.launch {
            try {
                val res = repository.register(register)

                if (res.statusCode == 200) success(res.message)
                else fail(res.message)


            } catch (e: Exception) {
                fail("Error: ${e.localizedMessage}")
            }
        }

    }


    private val _restaurants = MutableLiveData<List<FoodsCate>>()
    val restaurants get() = _restaurants

    fun getCategories(fail: (String) -> Unit) {

        viewModelScope.launch {
            try {
                _restaurants.value =  repository.getCategories()

            } catch (e: Exception) {
                fail("Error: ${e.localizedMessage}")
            }
        }
    }


}