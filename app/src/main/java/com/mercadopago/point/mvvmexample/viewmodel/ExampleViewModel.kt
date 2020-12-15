package com.mercadopago.point.mvvmexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mercadopago.point.mvvmexample.business.ExampleBusiness

class ExampleViewModel(private val business: ExampleBusiness) : ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val msg: MutableLiveData<String> = MutableLiveData("")

    suspend fun load() {
        business.callApi {
            loading.postValue(true)
            msg.postValue(it)
        }
    }
}