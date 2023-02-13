package indigo

import kotlin.NumberFormatException

class Human(deck: Deck): Player(deck, 6, "Player") {
    private fun printHand() {
        print("Cards in hand: ")
        cards.forEachIndexed { idx, card ->
            print("${idx+1})$card ")
        }
        print("\n")
    }
    override fun play(topCard: String): Int {
        this.resetCards()
        this.printHand()
        var answer: String
        while(true) {
            answer = readInput("Choose a card to play (1-${cards.size}):").lowercase()
            try {
                return if (answer != "exit") {
                    val value = answer.toInt() - 1
                    if (value in 0 until cards.size) value else throw NumberFormatException()
                } else Interaction.EXIT.code
            } catch (e: NumberFormatException) {
                continue
            }
        }
    }

}