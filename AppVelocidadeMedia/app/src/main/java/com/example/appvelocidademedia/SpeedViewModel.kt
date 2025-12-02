package com.example.appvelocidademedia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SpeedViewModel : ViewModel() {

    val distancia = MutableLiveData<String>()
    val tempo = MutableLiveData<String>()

    private val _velocidadeKmH = MutableLiveData<String>()
    val velocidadeKmH: LiveData<String> get() = _velocidadeKmH

    private val _velocidadeMS = MutableLiveData<String>()
    val velocidadeMS: LiveData<String> get() = _velocidadeMS

    private val _erro = MutableLiveData<String?>()
    val erro: LiveData<String?> get() = _erro

    fun calcular(){
        val d = distancia.value?.toDoubleOrNull()
        val t = tempo.value?.toDoubleOrNull()

        if (d == null || t == null) {
        _erro.value = "Introduce números válidos"
        _velocidadeKmH.value = ""
        _velocidadeMS.value = ""
        return
    }

    if (d <= 0 || t <= 0) {
        _erro.value = "Os valores deben ser maiores ca cero"
        _velocidadeKmH.value = ""
        _velocidadeMS.value = ""
        return
    }
        _erro.value = null
        _velocidadeKmH.value = String.format("%.2f km/h", d / t)
        _velocidadeMS.value = String.format("%.2f m/s", (d * 1000) / (t * 3600))
    }

    fun reset() {
        distancia.value = ""
        tempo.value = ""
        _velocidadeKmH.value = ""
        _velocidadeMS.value = ""
        _erro.value = null
    }
}