package com.example.exame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//ViewModel que almacena o gasto total. Úsase LiveData para que os fragments observen e se actualicen automaticamente
// Un ViewModel serve de ponte entre a lóxica e a interface
class GastoViewModel : ViewModel() {

    // LiveData interno que almacena o total do gasto, só o pode modificar o ViewModel. Inicialízase cun valor de 0.0. É privado para que só o ViewModel o poda cambiar
    private val _totalGasto = MutableLiveData(0.0)

    // LiveData público para observar dende os fragments. Os fragments non poden modificar
    val totalGasto: LiveData<Double> get() = _totalGasto

    // Dato observable que só o ViewModel pode modificar e que a interface pode observar para reaccionar a cambios (ex: mostrando un Toast ou navegando a outro fragment)
    private val _gastoGardado = MutableLiveData<Boolean>()
    val gastoGardado: LiveData<Boolean> get() = _gastoGardado

    // Metodo que suma un gasto ao total e avisa que foi gardado
    fun engadirGasto(cantidade: Double) {
        _totalGasto.value = (_totalGasto.value ?: 0.0) + cantidade
        _gastoGardado.value = true
    }

    // Metodo que resetea o gasto total a cero
    fun limparGasto() {
        _totalGasto.value = 0.0
    }
}
