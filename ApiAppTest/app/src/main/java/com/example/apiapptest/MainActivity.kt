package com.example.apiapptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.apiapptest.model.Serie
import com.example.apiapptest.viewmodel.SeriesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SeriesApp()
        }
    }
}

@Composable
fun SeriesApp(viewModel: SeriesViewModel = viewModel()) {
    val series by viewModel.series.collectAsState()
    viewModel.fetchSeries()
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        series?.let { SeriesList(series = it) }  // Comprueba si la respuesta de la API no es nula
    }
}

@Composable
fun SeriesList(series: List<Serie>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(series) { item ->
            SerieItem(serie = item) // Recorre la respuesta de la API y crea un item por cada serie en pantalla
        }
    }
}

@Composable
fun SerieItem(serie: Serie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp) // Bordes redondeados
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Fila para el título y el rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = serie.title,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.weight(1f)) // Espacio entre el título y el rating
                Text(
                    text = "⭐ ${serie.rating}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Espacio entre la fila y la imagen
            Spacer(modifier = Modifier.height(8.dp))

            // Imagen de la serie
            AsyncImage(
                model = serie.image, // URL de la imagen
                contentDescription = "Imagen de ${serie.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)), // Bordes redondeados
                contentScale = ContentScale.Crop // Ajusta la imagen al espacio disponible
            )
        }
    }
}