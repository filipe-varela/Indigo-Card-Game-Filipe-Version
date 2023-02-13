package indigo

fun String.removeLast() = substringBeforeLast(last())

fun readInput(question: String = ""): String {
    if (question.isNotEmpty()) println(question)
    return readln().removePrefix("> ")
}

private fun isFirstInput(): Boolean {
    var isFirst: String = readInput(IS_FIRST_QUESTION).lowercase()
    while (isFirst !in listOf("yes", "no")) isFirst = readInput(IS_FIRST_QUESTION).lowercase()
    return isFirst == "yes"
}

private fun printWinner(firstPlayer: Player, secondPlayer: Player) {
    println("${firstPlayer.name} wins cards")
    if (firstPlayer.name == PLAYER) printStatus(firstPlayer, secondPlayer)
    else printStatus(secondPlayer, firstPlayer)
}

private fun printStatus(player: Player, pc: Player, playerScore: Int = 0, pcScore: Int = 0) = println(
    "Score: Player ${if (playerScore == 0) player.score else playerScore} - " +
            "Computer ${if (pcScore == 0) pc.score else pcScore}\n" +
    "Cards: Player ${player._cards.size} - Computer ${pc._cards.size}"
)

const val PROJECT_INTRO = "Indigo Card Game"
const val IS_FIRST_QUESTION = "Play first?"
const val GAME_OVER = "Game Over"
const val PLAYER = "Player"
const val PC = "PC"
const val TABLE = "Table"

fun main() {
    println(PROJECT_INTRO)
    val deck = Deck()

    var decision = PC
    var firstPlayer = PC
    var secondPlayer = PLAYER

    if (isFirstInput()) {
        decision = PLAYER
        firstPlayer = PLAYER
        secondPlayer = PC
    }

    val players = mutableMapOf(
        PLAYER to Human(deck),
        PC to OpponentPC(deck),
        TABLE to Table(deck)
    )
    println()

    var cardIndex: Int
    var placeholderPlayer: String
    var winnerPlayer = ""
    var isExit = false

    while (true) {
        if (players[TABLE]!!.play() == Player.Interaction.EXIT.code) break

        cardIndex = players[firstPlayer]!!.play((players[TABLE]!! as Table)._topCard)
        if (cardIndex == Player.Interaction.EXIT.code) {
            isExit = true
            break
        }

        if (players[TABLE]!!.scored(cardIndex, players[firstPlayer]!!)) {
            printWinner(players[firstPlayer]!!, players[secondPlayer]!!)
            winnerPlayer = firstPlayer
        }

        placeholderPlayer = firstPlayer
        firstPlayer = secondPlayer
        secondPlayer = placeholderPlayer

        println()
    }

    if (!isExit) {
        players[winnerPlayer.ifEmpty { decision }]!!._cards.addAll(players[TABLE]!!.cards)
        var playerScore = players[PLAYER]!!.score
        var pcScore = players[PC]!!.score

        when {
            players[PLAYER]!!._cards.size > players[PC]!!._cards.size -> playerScore += 3
            players[PLAYER]!!._cards.size < players[PC]!!._cards.size -> pcScore += 3
            else -> {
                if (decision == PLAYER) playerScore += 3
                else pcScore += 3
            }
        }

        printStatus(players[PLAYER]!!, players[PC]!!, playerScore, pcScore)
    }

    println(GAME_OVER)
}
