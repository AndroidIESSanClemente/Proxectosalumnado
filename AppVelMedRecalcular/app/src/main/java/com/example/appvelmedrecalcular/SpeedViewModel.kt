package com.example.appvelmedrecalcular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SpeedViewModel : ViewModel() {

    //Datos introducidos polo usuario. Son strings porque veñen directamente da UI
    val distancia = MutableLiveData<String>()
    val tempo = MutableLiveData<String>()

    //Variables internas do ViewModel
    //MutableLiveData privadas para almacenar as velocidades
    //Só o ViewModel pode modificalas (_velocidadeKmH)
    //O Fragment/Activity só poden observar as versións públicas

    //resultados calculados
    private val _velocidadeKmH = MutableLiveData<String>()
    val velocidadeKmH: LiveData<String> get() = _velocidadeKmH

    private val _velocidadeMS = MutableLiveData<String>()
    val velocidadeMS: LiveData<String> get() = _velocidadeMS

    //Mensaxe de erro. Nullable para indicar que "non hai erro" con null
    private val _erro = MutableLiveData<String?>()
    val erro: LiveData<String?> get() = _erro

    //Función principal de cálculo de velocidade
    fun calcular(){

        //gardamos nunhas variables locais os textos dos campos

        val distanciaStr = distancia.value
        val tempoStr = tempo.value

        //Se algún campo está baleiro, non se calcula nin se mostra erro. Isto evita que aparezan toasts antes de tempo
        3
        if (distanciaStr.isNullOrBlank() || tempoStr.isNullOrBlank()) {
            _erro.value = null //non hai erro
            _velocidadeKmH.value = ""
            _velocidadeMS.value = ""
            return
        }

        //convertimos strings a Double, devolve null si é numérico
        val d = distancia.value?.toDoubleOrNull()
        val t = tempo.value?.toDoubleOrNull()

        //Valídase: se os datos introducidos non son números, móstrase un aviso de erro
        if (d == null || t == null) {
        _erro.value = "Introduce números válidos"
        _velocidadeKmH.value = "" //limpan os TextView que mostran resultados cando hai un erro. Así non se mostra a última velocidade calculada
        _velocidadeMS.value = ""
        return
    }

        //Validación: se son números <=0, móstrase outro erro diferente
    if (d <= 0 || t <= 0) {
        _erro.value = "Os valores deben ser maiores ca cero"
        _velocidadeKmH.value = ""
        _velocidadeMS.value = ""
        return
    }
        //Se os datos introducidos son correctos, calcúlase a velocidade. Convértense de double a string. .2f indica float con dous decimais
        _erro.value = null
        _velocidadeKmH.value = String.format("%.2f km/h", d / t)
        _velocidadeMS.value = String.format("%.2f m/s", (d * 1000) / (t * 3600))
    }
}