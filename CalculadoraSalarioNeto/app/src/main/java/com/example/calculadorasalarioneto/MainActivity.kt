package com.example.calculadorasalarioneto

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//  Asignamos variables a los campos de nuestra app

        val salarioBrutoEditText = findViewById<EditText>(R.id.salario)
        val numeroPagasSpinner = findViewById<Spinner>(R.id.pagas_spinner)
        val edadEditText = findViewById<EditText>(R.id.edad)
        val categoriaProfesionalSpinner = findViewById<Spinner>(R.id.categoria_profesional_spinner)
        val gradoDiscapacidadSpinner = findViewById<Spinner>(R.id.grado_discapacidad_spinner)
        val estadoCivilSpinner = findViewById<Spinner>(R.id.estado_civil_spinner)
        val numeroHijosEditText = findViewById<EditText>(R.id.numero_hijos)
        val button = findViewById<Button>(R.id.button)

//  Ejecución del botón de la APP
        button.setOnClickListener {

            // Validamos los campos
            if (salarioBrutoEditText.text.isNullOrEmpty() ||
                edadEditText.text.isNullOrEmpty() ||
                numeroHijosEditText.text.isNullOrEmpty()
            ) {
                // Mostrar mensaje de error
                Toast.makeText(this, "Por favor, rellena todos los campos obligatorios.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val ge: GestorEditText = GestorEditText()
            val gs: GestorSpinner = GestorSpinner()
            val calc: Calculo = Calculo()

            // Obtención de los coeficientes de cada campo

            val coefSalarioBruto =
                ge.coeficienteSalarioBruto(salarioBrutoEditText.text.toString().toDouble())
            val coefEdad = ge.coeficienteEdad(edadEditText.text.toString().toInt())
            val coefCategoria =
                gs.coeficienteCategoriaProfesional(categoriaProfesionalSpinner.selectedItemPosition)
            val coefDiscapacidad =
                gs.coeficienteGradoDiscapacidad(gradoDiscapacidadSpinner.selectedItemPosition)
            val coefEstadoCivil = gs.coeficienteEstadoCivil(estadoCivilSpinner.selectedItemPosition)
            val coefHijos = ge.coeficienteNumeroHijos(numeroHijosEditText.text.toString().toInt())

            // Realizamos los cálculos

            val salarioBruto = salarioBrutoEditText.text.toString().toDouble()

            val salarioNeto = calc.salarioNeto(
                salarioBruto,
                coefSalarioBruto,
                coefEdad,
                coefCategoria,
                coefDiscapacidad,
                coefEstadoCivil,
                coefHijos,
                numeroPagasSpinner.selectedItem.toString().toInt()
            )

            val retencionIRPF = calc.retencionIRPF(salarioNeto, salarioBruto)


            // Creamos el objeto intent que es el encargado de enviar los datos a la segunda pantalla de la app


            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("SALARIO_BRUTO", String.format("%.2f$", salarioBruto))
            intent.putExtra("SALARIO_NETO", String.format("%.2f$", salarioNeto))
            intent.putExtra("RETENCION_IRPF", String.format("%.2f$", retencionIRPF))


            //  Enviamos los datos a la segunda pantalla de la app

            startActivity(intent)

        }
    }
}