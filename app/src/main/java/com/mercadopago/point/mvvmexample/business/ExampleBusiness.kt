package com.mercadopago.point.mvvmexample.business

import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class ExampleBusiness {

    suspend fun callApi(success: (msg: String) -> Unit) {
        delay(TimeUnit.SECONDS.toMillis(7))
        success("It's me Mario! 🍄")
    }
}