package com.gob.scan_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gob.scan_app.databinding.ActivityInicioBinding
import com.google.zxing.integration.android.IntentIntegrator

class InicioActivity : AppCompatActivity() {


    private lateinit var binding: ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScanner.setOnClickListener { initScanner() }

        val intent = intent
        val mensaje1 = intent.getStringExtra("valor")
        println("AAAA" + mensaje1)

        binding.codigoScaneado.text = mensaje1

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    this,
                    "El valor escaneado es: " + result.contents,
                    Toast.LENGTH_LONG
                ).show()
                val intent = Intent(this, InicioActivity::class.java)
                intent.putExtra("valor", result.contents)
                startActivity(intent)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun initScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Prueba 123")
        integrator.setTorchEnabled(true)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

}



