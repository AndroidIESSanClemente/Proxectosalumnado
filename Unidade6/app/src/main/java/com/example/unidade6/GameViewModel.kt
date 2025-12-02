package com.example.unidade6

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.text.clear

class GameViewModel: ViewModel(){
    // Seleccionase unha palabra aleatoria desta lista de palabras
    val words = listOf("Android", "Fragment", "Kotlin", "Model")
    var secretWord = words.random().uppercase()
    // String que se mostrará na pantalla (guións e letras a medida que se van descubrindo)
    var secretWordDisplay = MutableLiveData<String>()
    // Intentos do usuario. Caracteres que vaia probando o usuario.
    //Engádese LiveData
    var guesses = mutableListOf<Char>()
    // Vidas
    var lives = MutableLiveData<Int>(8)

    val clearInput = MutableLiveData<Unit>()

    init {
        //Inicialízase a palabra
        secretWordDisplay.value = generateSecretWordDisplay()
    }

    //Xérase a representación visual da palabra oculta
    fun generateSecretWordDisplay()=
        //Recórrese cada un dos caracteres da palabra
        secretWord.map{
            //se a letra (it) xa está na lista, engadímola; senón substitúese por _
            if (it in guesses) it
            else '_'
        }.joinToString("")
    // xúntase nunha única cadea

    //Realízase un intendo de adiviñanza por parte do usuario
    //Primeiro compróbase que o xogador introduciu algo (guess.length >0)
    fun makeGuess(guess: String){
        if(guess.length > 0){
            //Extráese a letra inicial (aínda que só se poden introducir un caracter)
            val letter = guess.uppercase()[0]
            //Engádese esa letra á lista de letras intentadas (guesses)
            guesses.add(letter)
            //Actualízase o texto que se amosa con secretWordDisplay
            secretWordDisplay.value = generateSecretWordDisplay()
            if (!secretWord.contains(letter))
                lives.value =lives.value?.minus(1)
            //finalmente, se a letra non está na palabra secreta, réstase unha vida
            clearInput.value=Unit
        }
    }
    //Función para verificar se gañamos, comprobando se o que se mostra coincide coa palabra secreta
    fun win() = secretWord == secretWordDisplay.value
    //Función para comprobar se xa non quedan vidas, se se perdeu
    fun lost() = (lives.value ?: 0) <= 0

    fun restart() {
        guesses.clear()
        lives.value = 8
        secretWord = words.random().uppercase()
        secretWordDisplay.value = generateSecretWordDisplay()
    }

    fun resultMessage() =
        if (win()) "Gañaches! \n A palabra secreta era $secretWord"
        else "Ooh, perdiches! \n A palabra secreta era $secretWord"

}