package com.mercadopago.point.mvvmexample.model.dto

import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("id")
    val id: String,
    @SerializedName("employee_name")
    val name: String,
    @SerializedName("employee_salary")
    val salary: String,
    @SerializedName("employee_age")
    val age: String,
){
    override fun toString(): String {
        return "Id: ${this.id} Name: ${this.name} Age: ${this.age} Salary: ${this.salary}"
    }
}