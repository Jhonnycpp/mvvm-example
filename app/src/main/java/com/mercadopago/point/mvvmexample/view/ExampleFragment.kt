package com.mercadopago.point.mvvmexample.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mercadopago.point.mvvmexample.R
import com.mercadopago.point.mvvmexample.business.ExampleBusiness
import com.mercadopago.point.mvvmexample.viewmodel.ExampleViewModel
import kotlinx.android.synthetic.main.fragment_example.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ExampleFragment: Fragment() {

    private val viewModel = ExampleViewModel(ExampleBusiness())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_example, container, false)
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            viewModel.load()
        }
        this.createObservers()
    }

    private fun createObservers() {
        viewModel.loading.observe(this) {
            progressBar.visibility = if (!it) {
                 View.VISIBLE
            } else {
                 View.INVISIBLE
            }
        }
        viewModel.msg.observe(this) {
            textView.text = it
        }
    }
}