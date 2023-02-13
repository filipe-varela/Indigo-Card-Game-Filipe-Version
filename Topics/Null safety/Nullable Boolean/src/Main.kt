// You can experiment here, it won’t be checked

fun main(args: Array<String>) {
    val nullString: String? = null
    println(nullString?.isEmpty() == true)
    println(nullString?.isEmpty() != true)
    println(nullString?.isEmpty() == false)
    println(nullString?.isEmpty() != false)
}
