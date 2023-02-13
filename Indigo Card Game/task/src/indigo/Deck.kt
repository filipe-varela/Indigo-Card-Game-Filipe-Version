package indigo

class Deck {
    private val deck = mutableListOf<String>()

    val numberOfCardsMaximum = 52

    init {
        reset()
        shuffle()
    }

    fun reset() {
        deck.clear()
        for (s in suits) {
            for (r in ranks) {
                deck.add(r + s)
            }
        }
    }

    fun shuffle() {
        deck.shuffle()
    }

    fun getCards(n: Int): List<String> {
        val retList = mutableListOf<String>()
        if (n>deck.size) return listOf()
        else
            for (i in 0 until n)
                retList.add(deck.removeAt(0))
        return retList
    }

    fun printDeck() {
        println(ranks.joinToString(" "))
        println(suits.joinToString(" "))
        println(deck.joinToString(" "))
    }
}


val ranks = listOf(
    "A", "2", "3",
    "4", "5", "6",
    "7", "8", "9",
    "10", "J", "Q", "K"
)
val suits = listOf(
    "♠",
    "♥",
    "♦",
    "♣"
)
val candidates = listOf(
    "A",
    "J",
    "Q",
    "K",
    "10"
)