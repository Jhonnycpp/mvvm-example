package com.mercadopago.point.mvvmexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mercadopago.point.mvvmexample.business.ExampleBusiness
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class ExampleViewModel : ViewModel(), KoinComponent {
    val loading: MutableLiveData<Boolean> = MutableLiveData(true)
    val msg: MutableLiveData<String> = MutableLiveData("")

    private val business: ExampleBusiness by inject()

    suspend fun load() {
        business.callApi {
            msg.postValue(it)
            loading.postValue(false)
        }
    }
}