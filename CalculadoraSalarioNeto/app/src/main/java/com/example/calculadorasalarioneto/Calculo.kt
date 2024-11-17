package com.example.calculadorasalarioneto

class Calculo {

    fun salarioNeto(
        salarioBruto: Double, coefSalarioBruto: Double, coefEdad: Double,
        coefCategoria: Double, coefDiscapacidad: Double, coefEstadoCivil: Double,
        coefHijos: Double, numeroPagas: Int): Double {

        val coeficientes = coefSalarioBruto + coefEdad + coefCategoria + coefDiscapacidad + coefEstadoCivil + coefHijos
        return (salarioBruto * (1 - coeficientes)) / numeroPagas
    }


    fun retencionIRPF(salarioNeto: Double, salarioBruto: Double): Double {

        return salarioBruto - salarioNeto
    }
}