package com.mercadopago.point.mvvmexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mercadopago.point.mvvmexample.view.ExampleFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.createHandlers()
    }

    private fun createHandlers() {
        btnShowExample.setOnClickListener {
            with(supportFragmentManager.beginTransaction()) {
                replace(R.id.mainFlow, ExampleFragment())
                commit()
            }
        }
    }
}