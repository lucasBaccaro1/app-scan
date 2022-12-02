package com.gob.scan_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.gob.scan_app.core.Resource
import com.gob.scan_app.data.remote.LoginDataSource
import com.gob.scan_app.databinding.ActivityMainBinding
import com.gob.scan_app.repository.LoginRepositoryImpl
import com.gob.scan_app.repository.RetrofitClient
import com.gob.scan_app.ui.presentation.LoginViewModelFactory
import com.gob.scan_app.ui.presentation.LoginViewmodel

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding


    private val viewModel by viewModels <LoginViewmodel> {
        LoginViewModelFactory(
            LoginRepositoryImpl(
                LoginDataSource(RetrofitClient.webservice),
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var resultado : Boolean? = null

        binding.nextButton.setOnClickListener {

            viewModel.getLogin(binding.opeardor.text.toString(),binding.password.text.toString()).observe(this@MainActivity, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        println("cargando...")
                    }
                    is Resource.Success -> {
                        resultado = result.data.login

                        if(resultado == true){
                            val intentInicio = Intent(applicationContext,InicioActivity::class.java)
                            startActivity(intentInicio)
                        }
                    }
                    //Capturo error de servicio
                    is Resource.Failure -> {
                        Log.d("Error", "${result.exception}")
                    }
                }
            })



        }


    }
}