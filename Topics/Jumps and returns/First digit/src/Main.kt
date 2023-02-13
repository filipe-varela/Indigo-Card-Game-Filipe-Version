fun main() {
    val prompt = readln()
    for (s in prompt) {
        if (s.isDigit()) {
            println(s)
            break
        }
    }
}