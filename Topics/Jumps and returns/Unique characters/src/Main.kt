fun main() {
    val prompt = readln().lowercase()
    val uniqueLetters = mutableListOf<Char>()
    val repeatedLetters = mutableListOf<Char>()
    var letter: Char
    forAll@ for (i in prompt.indices) {
        letter = prompt[i]
        if (letter !in repeatedLetters && letter.isLetter()) {
            for (j in i+1 until prompt.length) {
                if (letter == prompt[j]) {
                    repeatedLetters.add(letter)
                    continue@forAll
                }
            }
            uniqueLetters.add(letter)
        }
    }
    println(uniqueLetters.size)
}