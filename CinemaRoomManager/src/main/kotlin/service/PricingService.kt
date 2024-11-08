package service

import model.Room

class PricingService {

    val PREMIUM_TICKET = 10
    val REGULAR_TICKET = 8

    fun totalIncome() {
        println("Enter the number of rows:")
        val rows = readln().toInt()
        println("Enter the number of seats in each row:")
        val seatsPerRow = readln().toInt()

        val capacity = rows * seatsPerRow
        var income = 0

        if (capacity <= 60) {
            income = 10 * capacity
        } else {
            val frontHalfRows = rows / 2 // Redondea hacia abajo
            val backHalfRows = rows - frontHalfRows

            // Calcular el ingreso de los asientos en la primera mitad (precio 10)
            income += frontHalfRows * seatsPerRow * PREMIUM_TICKET

            // Calcular el ingreso de los asientos en la segunda mitad (precio 8)
            income += backHalfRows * seatsPerRow * REGULAR_TICKET
        }
        println("Total income:")
        println("$$income")
    }

    fun ticketPrice(room: Room, position: Pair<Int, Int>) {
        val rs = RoomService()
        val (row, seat) = position

        // Calculamos el número total de asientos en la sala
        val roomCapacity = room.rowList.size * room.rowList[0].seatsList.size

        // Determinamos el precio del ticket basándonos en la capacidad de la sala y la fila
        if (roomCapacity <= 60) {
            // Si la sala tiene 60 o menos asientos, todos los ticket son "REGULAR"
            println("Ticket price: $$PREMIUM_TICKET")
        } else {
            // Si la sala tiene más de 60 asientos, calculamos el precio según la fila
            if (row <= room.rowList.size / 2) {
                // La primera mitad de las filas tiene tickets "PREMIUM"
                println("Ticket price: $$PREMIUM_TICKET")
            } else {
                // La segunda mitad de las filas tiene tickets "PREMIUM"
                println("Ticket price: $$REGULAR_TICKET")
            }
        }
    }
}
