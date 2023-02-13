package indigo

class OpponentPC(deck: Deck): Player(deck, 6, "Computer") {
    override fun play(topCard: String): Int {
        this.resetCards()

        println(cards.joinToString(" "))

        val answer = filterSingleCard(cards, topCard).random()
        println("Computer plays ${cards[answer]}")

        return answer
    }
}

private fun filterSingleCard(cards: MutableList<String>, topCard: String): List<Int> {
    return if (cards.size == 1) listOf(0) else filterCandidates(
        cards.mapIndexed{id, s -> "$id $s"}.toMutableList(),
        topCard
    )
}

private fun filterCandidates(indexedCards: MutableList<String>, topCard: String): List<Int> {
    return if (topCard.isEmpty()) filterCandidatesWithEmptyTable(indexedCards)
    else filterCandidatesWithTable(indexedCards, topCard)
}

fun filterCandidatesWithTable(indexedCards: MutableList<String>, topCard: String): List<Int> {
    val checkCandidates: (String) -> Boolean = {
        val card = it.split(" ").last()
        card.last() == topCard.last() || card.removeLast() == topCard.removeLast()
    }

    val candidateCards = indexedCards.filter(checkCandidates).toMutableList()
    return when {
        candidateCards.isEmpty() -> filterCandidatesWithEmptyTable(indexedCards)
        candidateCards.size == 1 -> listOf(candidateCards[0].split(" ").first().toInt())
        else -> filterCandidatesWithEmptyTable(candidateCards.toMutableList())
    }
}

private fun filterCandidatesWithEmptyTable(indexedCards: MutableList<String>): List<Int> {
    val repeatedSuits = getSuitsCardsMap(indexedCards)
    return if (repeatedSuits.isNotEmpty()) repeatedSuits[repeatedSuits.keys.random()]!! else {
        val repeatedRanks = getRankCardsMap(indexedCards)
        return if (repeatedRanks.isNotEmpty()) repeatedRanks[repeatedRanks.keys.random()]!!
        else indexedCards.map{it.split(" ")[0].toInt() }.toMutableList()
    }
}

private fun getRankCardsMap(indexedCards: MutableList<String>): Map<String, MutableList<Int>> {
    val orderCardsByRank = indexedCards.toMutableList()
    orderCardsByRank.sortBy { it.split(" ").last().removeLast() }
    val rankCards = mutableMapOf<String, MutableList<Int>>().apply {
        for (suit in ranks) put(suit, mutableListOf())
    }
    for (c in orderCardsByRank.indices) {
        rankCards[orderCardsByRank[c].split(" ").last().removeLast()]!!.add(
            orderCardsByRank[c].split(" ").first().toInt()
        )
    }
    return rankCards.filterValues { it.size > 1 }
}

private fun getSuitsCardsMap(cards: MutableList<String>): Map<String, MutableList<Int>> {
    val orderCardsBySuit = cards.toMutableList()
    orderCardsBySuit.sortBy { it.last() }
    val suitsCards = mutableMapOf<String, MutableList<Int>>().apply {
        for (suit in suits) put(suit, mutableListOf())
    }
    for (c in orderCardsBySuit.indices) {
        suitsCards[orderCardsBySuit[c].last().toString()]!!.add(
            orderCardsBySuit[c].split(" ").first().toInt()
        )
    }
    return suitsCards.filterValues { it.size > 1 }
}