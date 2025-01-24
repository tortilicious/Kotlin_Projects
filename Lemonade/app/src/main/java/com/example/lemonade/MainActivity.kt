package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp () {
    ImageCarousel(modifier = Modifier)
    
}

@Composable
private fun ImageCarousel(modifier: Modifier = Modifier) {
    // Lista de imágenes
    val images = listOf(
        R.drawable.lemon_tree,
        R.drawable.lemon_squeeze,
        R.drawable.lemon_drink,
        R.drawable.lemon_restart
    )

    // Lista de textos
    val texts = listOf(
        "Tap the lemon tree to select a lemon",
        "Keep tapping the lemon to squeeze it",
        "Tap the lemonade to drink it",
        "Tap the empty glass to start again"
    )

    // Índice para rastrear la imagen actual
    val currentIndex = remember { mutableIntStateOf(0) }

    // Contador de clics en la segunda imagen
    val tapCounter = remember { mutableIntStateOf(0) }

    // Número de clics requeridos para la segunda imagen
    val squeezeCounter = remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.lemonade),
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .padding(16.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    when (currentIndex.intValue) {
                        0 -> {
                            // Primera imagen: Generar un número aleatorio y avanzar a la segunda imagen
                            squeezeCounter.intValue = (2..4).random()
                            currentIndex.intValue++
                        }
                        1 -> {
                            // Segunda imagen: Incrementar el contador de clics
                            tapCounter.intValue++
                            // Si se alcanza el número de clics requeridos, avanzar a la tercera imagen
                            if (tapCounter.intValue >= squeezeCounter.intValue) {
                                currentIndex.intValue++
                                tapCounter.intValue = 0 // Reiniciar el contador de clics
                            }
                        }
                        3 -> {
                            // Cuarta imagen: Reiniciar el ciclo
                            currentIndex.intValue = 0
                        }
                        else -> {
                            // Tercera imagen: Avanzar a la siguiente imagen
                            currentIndex.intValue++
                        }
                    }
                },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.wrapContentSize()
            ) {
                Image(
                    painter = painterResource(images[currentIndex.intValue]),
                    contentDescription = "Lemon tree",
                    modifier = Modifier.wrapContentSize()
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = if (currentIndex.intValue == 1) {
                    "Tap the lemon ${tapCounter.intValue}/${squeezeCounter.intValue} times"
                } else {
                    texts[currentIndex.intValue]
                },
                fontSize = 18.sp
            )
        }
    }
}