package indigo

class Table(deck: Deck) : Player(deck, 4) {
    var _topCard = cards.last()

    init {
        _cards.addAll(cards)
        println("Initial cards on the table: " + cards.joinToString(" "))
    }

    override fun play(topCard: String): Int {
        println(
            if (cards.isEmpty()) "No cards on the table"
            else "${cards.size} cards on the table, and the top card is ${cards.last()}"
        )
        return if (_cards.size == 52) Interaction.EXIT.code else Interaction.NO_INTERACTION.code
    }

    override fun scored(idx: Int, player: Player): Boolean {
        val currentCard = player.cards.removeAt(idx)
        _cards.add(currentCard)
        cards.add(currentCard)
        return if (
            _topCard.isNotEmpty() && (
                    currentCard.substringBefore(currentCard.last()) == _topCard.substringBefore(_topCard.last()) ||
                            currentCard.last() == _topCard.last()
                    )
        ) {
            player._cards.addAll(cards)
            cards.clear()
            _topCard = ""
            true
        } else {
            _topCard = cards.last()
            false
        }
    }
}

