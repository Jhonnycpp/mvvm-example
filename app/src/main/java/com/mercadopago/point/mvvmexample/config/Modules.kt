package com.mercadopago.point.mvvmexample.config

import com.mercadopago.point.mvvmexample.BuildConfig
import com.mercadopago.point.mvvmexample.api.EmployeesApi
import com.mercadopago.point.mvvmexample.business.EmployeeBusiness
import com.mercadopago.point.mvvmexample.business.ExampleBusiness
import com.mercadopago.point.mvvmexample.view.LoadingFragment
import com.mercadopago.point.mvvmexample.viewmodel.EmployeeListViewModel
import com.mercadopago.point.mvvmexample.viewmodel.ExampleViewModel
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.androidx.viewmodel.dsl.viewModel

val retrofitModules = module(override = true) {
    fun provideGson() = GsonConverterFactory.create()

    fun provideOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder.build()
    }

    fun provideRetrofit(gson: GsonConverterFactory, okHttp: OkHttpClient) = Retrofit
        .Builder()
        .baseUrl("https://dummy.restapiexample.com/")
        .addConverterFactory(gson)
        .build()

    fun provideEmployeeApi(retrofit: Retrofit) = retrofit.create(EmployeesApi::class.java)

    single { provideGson() }
    single { provideOkHttp() }
    single { provideRetrofit(get(), get()) }
    single { provideEmployeeApi(get()) }
}

val businessModule = module(override = true) {
    single { ExampleBusiness() }
    single { EmployeeBusiness() }
}

val viewModelModule = module(override = true) {
    viewModel { ExampleViewModel() }
    viewModel { EmployeeListViewModel() }
}

val anotherModules = module(override = true) {
    factory { LoadingFragment() }
}

val allModules = listOf(retrofitModules, businessModule, viewModelModule, anotherModules)