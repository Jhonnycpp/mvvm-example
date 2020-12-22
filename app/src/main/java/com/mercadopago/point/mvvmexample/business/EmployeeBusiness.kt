package com.mercadopago.point.mvvmexample.business

import android.util.Log
import com.mercadopago.point.mvvmexample.api.EmployeesApi
import com.mercadopago.point.mvvmexample.model.dto.Employee
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@KoinApiExtension
class EmployeeBusiness: KoinComponent {
    private val employeesApi: EmployeesApi by inject()

    suspend fun getEmployees(success: (List<Employee>) -> Unit): Boolean {
        return try {
            val response = employeesApi.getList()
            if(response.isSuccessful) {
                response.body()?.data?.let { success(it) }
                false
            } else {
                true
            }
        } catch (e: Exception) {
            Log.d("Business", "getEmployees: fail $e")
            true
        }
    }
}