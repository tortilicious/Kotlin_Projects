package model

class Row (
    var rowNumber: Int = 0,
    val seatsList: MutableList<Seat> = mutableListOf()) {
}