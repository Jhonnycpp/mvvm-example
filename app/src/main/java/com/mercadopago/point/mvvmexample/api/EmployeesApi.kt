package com.mercadopago.point.mvvmexample.api

import com.mercadopago.point.mvvmexample.model.dto.EmployeeList
import retrofit2.Response
import retrofit2.http.GET

interface EmployeesApi {
    @GET("/api/v1/employees")
     suspend fun getList(): Response<EmployeeList>
}