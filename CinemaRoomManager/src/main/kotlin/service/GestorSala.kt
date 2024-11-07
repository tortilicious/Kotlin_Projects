package service
import model.Asiento
import model.Fila
import model.Sala

class GestorSala() {

    private val PRECIO_ASIENTO_DELANTERO = 10
    private val PRECIO_ASIENTO_TRASERO = 8

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

    fun calcularPrecio(sala: Sala): Int {
        val filas = sala.listaFilas.size
        val asientos = sala.listaFilas[0].listaAsientos.size
        val aforo = filas * asientos

        return if (aforo <= 60) {
            // Precio fijo para sala pequeña
            filas * asientos * PRECIO_ASIENTO_DELANTERO
        } else {
            // Sala grande: calculamos por filas delanteras y traseras
            val filasDelanteras = filas / 2
            val filasTraseras = filas - filasDelanteras

            (filasDelanteras * asientos * PRECIO_ASIENTO_DELANTERO) +
                    (filasTraseras * asientos * PRECIO_ASIENTO_TRASERO)
        }
    }
}