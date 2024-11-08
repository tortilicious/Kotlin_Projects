package service
import model.Room
import model.Row
import model.Seat

class RoomService {

    fun createRoom(): Room {
        println("Enter the number of rows:")
        val rows = readln().toInt()
        println("Enter the number of seats in each row:")
        val seats = readln().toInt()

        val rowsList = mutableListOf<Row>()
        for (i in 1..rows) {
            val row = Row()
            row.rowNumber = i
            for (j in 1..seats) {
                val seat = Seat()
                seat.seatNumber = j
                row.seatsList.add(seat)
            }
            rowsList.add(row)
        }
        return Room(rowsList)
    }

    fun showRoom(room: Room) {

        val header = mutableListOf<Int>()
        for (i in 1..room.rowList[0].seatsList.size) {
            header.add(i)
        }
        println("Cinema:")
        print(" ")
        println(header.joinToString(separator = " ", prefix = " "))

        var counter = 1
        for (j in 1..room.rowList.size) {
            print (counter)
            ++counter
            for (seat in room.rowList[j - 1].seatsList) {
                if (seat.available) {
                    print(" S")
                } else {
                    print(" B")
                }
            }
            print("\n")
        }
    }

    fun seatReserve(room: Room, position: Pair<Int, Int>) {

        val (row, seat) = position
        // Validar si el número de fila está dentro del rango de filas de la sala
        if (row !in 1..room.rowList.size) {
//            println("Invalid row number.")
            return
        }

        // Obtener la fila específica
        val targetRow = room.rowList[row - 1]

        // Validar si el número de asiento está dentro del rango de asientos de la fila
        if (seat !in 1..targetRow.seatsList.size) {
//            println("Invalid seat number.")
            return
        }

        // Obtener el asiento específico y verificar si está disponible
        val targetSeat = targetRow.seatsList[seat - 1]
        if (!targetSeat.available) {
//            println("This seat is already reserved.")
        } else {
            // Reservar el asiento si está disponible
            targetSeat.available = false
//            println("Seat reserved successfully.")
        }


    }

    fun askRowSeats(): Pair<Int, Int> {
        println("Enter a row number:")
        val row = readln().toInt()
        println("Enter a seat number in that row:")
        val seats = readln().toInt()

        return Pair(row, seats)
    }
}