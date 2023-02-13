package indigo

open class Player(
    private val deck: Deck,
    private val n: Int,
    val name: String = ""
) {
    val cards = deck.getCards(n).toMutableList()
    val _cards = mutableListOf<String>()
    val score: Int
        get() {
            var internalScore = 0
            for (c in _cards) internalScore += if (c.substringBefore(c.last()) in candidates
            ) 1 else 0
            return internalScore
        }

    enum class Interaction(val code: Int) {
        NO_INTERACTION(-2),
        EXIT(-3);
    }
    open fun play(topCard: String = ""): Int = Interaction.NO_INTERACTION.code
    fun resetCards() { if (cards.isEmpty()) cards.addAll(deck.getCards(n)) }

    open fun scored(idx: Int, player: Player): Boolean = false
}