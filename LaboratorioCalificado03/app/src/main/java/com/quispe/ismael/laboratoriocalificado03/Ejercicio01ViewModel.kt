package com.quispe.ismael.laboratoriocalificado03

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Ejercicio01ViewModel : ViewModel() {

    val profesorList = MutableLiveData<List<Profesor>>()
    val isLoading = MutableLiveData<Boolean>()
    val errorApi = MutableLiveData<String>()

    init {
        getAllProfesores()
    }

    private fun getAllProfesores() {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit().create(ProfesorApi::class.java).getProfesores()
                if (call.isSuccessful) {
                    call.body()?.let { profesorResponse ->
                        isLoading.postValue(false)
                        profesorList.postValue(profesorResponse.teachers)
                    }
                } else {
                    errorApi.postValue("Error al obtener los datos de la API.")
                    isLoading.postValue(false)
                }
            } catch (e: Exception) {
                errorApi.postValue(e.message ?: "Error desconocido")
                isLoading.postValue(false)
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
