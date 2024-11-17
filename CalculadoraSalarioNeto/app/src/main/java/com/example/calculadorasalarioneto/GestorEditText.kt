package com.example.calculadorasalarioneto

import android.widget.EditText

//  Clase encargada de las fuciones que van a pasar el input de los EditText
//  a las variables que elijamos.

class GestorEditText {
    fun obtenerDecimal(decimalEditText: EditText): Double {
        val valorDecimal = decimalEditText.text.toString().toDoubleOrNull()
        return valorDecimal ?: 0.0
    }

    fun obtenerEntero(entero: EditText): Int {
        val valorEntero = entero.text.toString().toIntOrNull()
        return valorEntero ?: 0
    }

    fun coeficienteEdad(edad: Int): Double {
        return when {
            edad <= 25 -> 0.05
            edad in 26..40 -> 0.07
            edad in 41..55 -> 0.09
            edad > 55 -> 0.05
            else -> 0.0
        }
    }

    fun coeficienteNumeroHijos(numeroHijos: Int): Double {
        when {
            numeroHijos == 0 -> return 0.05
            numeroHijos in 1..3 -> return 0.03
            numeroHijos in 4..5 -> return 0.01
            numeroHijos > 5 -> return 0.0
            else -> return 0.0
        }
    }

    fun coeficienteSalarioBruto(salarioBruto: Double): Double {
        when {
            salarioBruto < 15000 -> return 0.01
            salarioBruto < 20000 -> return 0.025
            salarioBruto < 25000 -> return 0.03
            salarioBruto < 35000 -> return 0.04
            salarioBruto < 50000 -> return 0.05
            salarioBruto < 75000 -> return 0.07
            salarioBruto > 100000 -> return 0.1
            else -> return 0.0
        }
    }

}