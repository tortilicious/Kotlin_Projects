import java.util.*

//Start of Kotlin Program
fun main(args: Array<String>) {

    //Create a scan instance for readling line from standard Input
    val scan = Scanner(System.`in`)

    // Reading an integer as input
    var userInput = scan.nextLine().trim()

    //TODO:
    //You need to Check:
    //If the user input is an integer
    //If the user input is between 1 and 9999 (inclusive)
    //If not, print 'Invalid Input'.
    //Otherwise, calculate the sum of the individual digits of this integer.

    try {
        val number = userInput.trim().toInt()
        var tempNumber = number
        var sum = 0

        if (number in 1..9999) {
            while (tempNumber > 0) {
                sum += tempNumber % 10
                tempNumber /= 10
            }
            println(sum)
        } else {
            println("Invalid Input")
        }
    } catch (e: NumberFormatException) {
        println("Invalid Input")
    }
}
// End of Kotlin Program