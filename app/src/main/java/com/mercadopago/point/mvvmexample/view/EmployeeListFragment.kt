package com.mercadopago.point.mvvmexample.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.mercadopago.point.mvvmexample.R
import com.mercadopago.point.mvvmexample.model.dto.Employee
import com.mercadopago.point.mvvmexample.viewmodel.EmployeeListViewModel
import kotlinx.android.synthetic.main.fragment_employee_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class EmployeeListFragment: Fragment() {
    private val viewModel: EmployeeListViewModel by inject()
    private val loading: LoadingFragment by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_employee_list, container, false)
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
            with(this.childFragmentManager.beginTransaction()) {
                if (it) {
                    add(R.id.fragmentFlow, loading)
                } else {
                    remove(loading)
                }
                commit()
            }
        }

        viewModel.employees.observe(this) {
            val adapter = ArrayAdapter<Employee>(this.requireActivity(), android.R.layout.simple_list_item_1, it)
            listEmployees.adapter = adapter
        }

        viewModel.error.observe(this) {
            if(it) {
                val builder = AlertDialog.Builder(this.activity)
                builder.setTitle("Ops, ocorreu um erro!")
                builder.setMessage("Desculpe mas não consegui obter a lista de usuários.")
                builder.show()
                this.activity?.let{ activity ->
                    with(activity.supportFragmentManager.beginTransaction()) {
                        remove(this@EmployeeListFragment)
                        commit()
                    }
                }
            }
        }
    }
}