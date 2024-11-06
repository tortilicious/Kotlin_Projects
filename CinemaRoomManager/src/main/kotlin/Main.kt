import service.GestorSala

fun main() {

    val gestorSala = GestorSala()
    val sala = gestorSala.generarSala()

    gestorSala.mostrarSala(sala)
}