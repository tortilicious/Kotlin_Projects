package cinema

import service.PricingService
import service.RoomService

fun main() {
    // Creamos objetos de las clases encargadas de la logica de negocio
    val rs = RoomService()
    val ps = PricingService()

    // Creamos nuestra sala de cine y mostramos el estado inicial de las butacas
    var room = rs.createRoom()
    rs.showRoom(room)
    println()

    // Almacenamos la posicion del ticket que queremos comprar
    var position = rs.askRowSeats()

    // Creamos la reserva de la butaca
    rs.seatReserve(room, position)

    // Mostramos por pantalla el precio del ticket en funcion del tamaño de la sala y la posicion de la fila
    ps.ticketPrice(room, position)
    println()

    // Mostramos el estado actualizado de la sala
    rs.showRoom(room)

}