// complete this function, add default values
fun carPrice(
    old: Int = 5,
    kilometers: Int = 100_000,
    maximumSpeed: Int = 120,
    automatic: Boolean = false
){
    val costPerYear = 2_000
    val costPerKilometerPerHour = 100
    val speedThreshold = 120
    val costPerKilometer = 200
    val costPerKilometerStep = 10_000
    val costNotAutomatic = 1_500

    var carsValue = 20_000
    carsValue -= old * costPerYear
    carsValue += (maximumSpeed - speedThreshold) * costPerKilometerPerHour
    carsValue -= (kilometers / costPerKilometerStep) * costPerKilometer
    carsValue += if (automatic) costNotAutomatic else 0

    println("$carsValue")
}

//fun main() = carPrice(kilometers = 20_000)