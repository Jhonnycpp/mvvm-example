package com.mercadopago.point.mvvmexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mercadopago.point.mvvmexample.business.EmployeeBusiness
import com.mercadopago.point.mvvmexample.model.dto.Employee
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class EmployeeListViewModel:ViewModel(), KoinComponent {
    private val business: EmployeeBusiness by inject()
    val loading: MutableLiveData<Boolean> = MutableLiveData(true)
    val employees: MutableLiveData<List<Employee>> = MutableLiveData()
    val error: MutableLiveData<Boolean> = MutableLiveData(false)

    suspend fun load() {
        val fail = business.getEmployees {
            employees.postValue(it)
            loading.postValue(false)
        }
        error.postValue(fail)
    }
}