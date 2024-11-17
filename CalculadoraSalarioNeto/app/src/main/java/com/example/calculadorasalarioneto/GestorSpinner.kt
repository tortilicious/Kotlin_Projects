package com.example.calculadorasalarioneto

class GestorSpinner {

    fun coeficienteEstadoCivil(estadoCivilArrayPosition: Int): Double {
        return when (estadoCivilArrayPosition) {
            0 -> 0.03
            1 -> 0.05
            2 -> 0.05
            else -> 1.0
        }
    }

    fun coeficienteGradoDiscapacidad(gradoDiscapacidadArrayPosition: Int): Double {
        return when (gradoDiscapacidadArrayPosition) {
            0 -> 0.05
            1 -> 0.04
            2 -> 0.03
            3 -> 0.02
            4 -> 0.0
            else -> 1.0
        }
    }

    fun coeficienteCategoriaProfesional(categoriaProfesionalArrayPosition: Int): Double {
        return when (categoriaProfesionalArrayPosition) {
            0 -> 0.08
            1 -> 0.07
            2 -> 0.06
            3 -> 0.05
            4 -> 0.04
            5 -> 0.03
            6 -> 0.02
            7 -> 0.01
            else -> 1.0
        }
    }
}
