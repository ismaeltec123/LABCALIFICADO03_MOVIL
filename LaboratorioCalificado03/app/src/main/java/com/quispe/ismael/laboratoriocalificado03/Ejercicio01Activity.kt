package com.quispe.ismael.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.quispe.ismael.laboratoriocalificado03.databinding.ActivityEjercicio01Binding

class Ejercicio01Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding
    private lateinit var adapter: ProfesorAdapter

    private val viewModel: Ejercicio01ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityEjercicio01Binding.inflate(layoutInflater)
            setContentView(binding.root)

            setupRecyclerView()
            observeViewModel()
        } catch (e: Exception) {
            Log.e("Ejercicio01Activity", "Error en onCreate: ${e.message}", e)
        }
    }

    private fun setupRecyclerView() {
        adapter = ProfesorAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        adapter.setOnItemClickListener { profesor ->
            callProfesor(profesor.phone)
        }

        adapter.setOnItemLongClickListener { profesor ->
            sendEmail(profesor.email)
        }
    }

    private fun observeViewModel() {

        viewModel.profesorList.observe(this) { profesores ->
            adapter.list = profesores
            adapter.notifyDataSetChanged()
        }


        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.errorApi.observe(this) { errorMessage ->
            showMessage(errorMessage)
        }
    }

    private fun callProfesor(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }

    private fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
        startActivity(intent)
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
