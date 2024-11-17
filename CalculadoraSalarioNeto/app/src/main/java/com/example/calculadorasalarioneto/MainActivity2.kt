package com.example.calculadorasalarioneto

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Obtenemos las referencias a los TextView de la segunda pantalla
        val salarioBrutoTextView = findViewById<TextView>(R.id.salario_bruto_result)
        val salarioNetoTextView = findViewById<TextView>(R.id.salario_neto_result)
        val retencionIRPFTextView = findViewById<TextView>(R.id.retencion_irpf_result)

        // Recuperamos los datos enviados desde MainActivity
        val salarioBruto = intent.getStringExtra("SALARIO_BRUTO")
        val salarioNeto = intent.getStringExtra("SALARIO_NETO")
        val retencionIRPF = intent.getStringExtra("RETENCION_IRPF")

        // Mostrar los datos en los TextView
        salarioBrutoTextView.text = "$salarioBruto"
        salarioNetoTextView.text = "$salarioNeto"
        retencionIRPFTextView.text = "$retencionIRPF"
    }
}