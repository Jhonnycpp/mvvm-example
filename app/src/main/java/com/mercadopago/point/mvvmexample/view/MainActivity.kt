package com.mercadopago.point.mvvmexample.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mercadopago.point.mvvmexample.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        this.createHandlers()
    }

    private fun createHandlers() {
        btnShowExample.setOnClickListener {
            with(supportFragmentManager.beginTransaction()) {
                replace(R.id.viewFlow, ExampleFragment())
                commit()
            }
        }

        btnEmployeeList.setOnClickListener {
            with(supportFragmentManager.beginTransaction()) {
                replace(R.id.viewFlow, EmployeeListFragment())
                commit()
            }
        }
    }
}