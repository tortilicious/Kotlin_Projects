package service
import model.Asiento
import model.Fila
import model.Sala

class GestorSala() {

    fun generarSala(): Sala {

        println("Ingresa el número de filas:")
        val numeroFilas = readln().toInt()
        println("Ingresa el número de asientos por fila:")
        val numeroAsientos = readln().toInt()

        val listaFilas = mutableListOf<Fila>()
        for (i in 1..numeroFilas) {
            val fila = Fila()
            for (j in 1..numeroAsientos) {
                val asiento = Asiento()
                asiento.numeroAsiento = j
                fila.listaAsientos.add(asiento)
            }
            fila.numeroFila = i
            listaFilas.add(fila)
        }
        return Sala("Cine Miguel", listaFilas)
    }

    fun mostrarSala(sala: Sala) {
        var contador = 1
        print(" ")

        for (i in 1..sala.listaFilas[0].listaAsientos.size) {
            print(" $i")
        }
        println() // salto de línea tras la impresíón del header

        for (fila in sala.listaFilas) {
            print("$contador")
            ++contador
            for(asiento in fila.listaAsientos) {
                if (asiento.ocupado){
                    print(" X")
                } else {
                    print(" O")
                }
            }
            println()
        }
    }
}