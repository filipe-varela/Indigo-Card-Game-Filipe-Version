fun main() {
    val index = readLine()!!.toInt()
    val word = readLine()!!
    println(
        if (index < word.length && -1 < index) word[index]
        else "There isn't such an element in the given string, please fix the index!"
    )
}