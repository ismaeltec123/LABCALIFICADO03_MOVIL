package com.quispe.ismael.laboratoriocalificado03

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.quispe.ismael.laboratoriocalificado03.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.btnIrLista.setOnClickListener {
                val intent = Intent(this, Ejercicio01Activity::class.java)
                startActivity(intent)
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Error en onCreate: ${e.message}", e)
        }
    }



    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}