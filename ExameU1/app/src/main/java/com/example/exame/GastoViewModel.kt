package com.example.exame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//ViewModel que almacena o gasto total. Úsase LiveData para que os fragments observen e se actualicen automaticamente

class GastoViewModel : ViewModel() {

    // LiveData interno que almacena o total, só o pode modificar o ViewModel
    private val _totalGasto = MutableLiveData(0.0)

    // LiveData público para observar dende os fragments
    val totalGasto: LiveData<Double> get() = _totalGasto

    // Este LiveData poñerase en 'true' cando o gasto se garde
    private val _gastoGardado = MutableLiveData<Boolean>()
    val gastoGardado: LiveData<Boolean> get() = _gastoGardado

    // Metodo que suma un gasto ao total
    fun engadirGasto(cantidade: Double) {
        _totalGasto.value = (_totalGasto.value ?: 0.0) + cantidade
        _gastoGardado.value = true
    }

    // Metodo que resetea o gasto total a cero
    fun limparGasto() {
        _totalGasto.value = 0.0
    }
}
