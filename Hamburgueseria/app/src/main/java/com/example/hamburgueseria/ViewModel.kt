package com.example.hamburgueseria

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//Clase que herda de ViewModel e que serve para gardar o total do pedido
class OrderViewModel: ViewModel() {
    //Variable privada que garda o total do pedido. O guión baixo indica uso interno. De tipo Double (número con decimais) e cun valor inicial de 0.0
    private val _total = MutableLiveData<Double>(0.0)
    //Variable pública total. Os fragments poden ver o seu valor pero non o poden modificar
    val total : LiveData<Double> = _total
    //Función para engadir un pedido. Novo total = total actual + prezo. ?: 0.0 indica que se o total é nulo utilízase 0.0 para a suma

    private val _name = MutableLiveData<String>("")
    val name: LiveData<String> = _name
    fun addOrder(price: Double, userName: String){
        _total.value = (_total.value ?: 0.0) + price
        _name.value = userName
    }
    //Función para borrar o pedido que pon o total de novo a 0.0
    fun clear(){
        _total.value = 0.0
        _name.value = ""
    }
}