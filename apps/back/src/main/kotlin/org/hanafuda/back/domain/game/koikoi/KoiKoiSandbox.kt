package org.hanafuda.back.domain.games.koikoi

import org.hanafuda.back.domain.core.models.Card
import org.hanafuda.back.domain.core.models.CardType
import org.hanafuda.back.domain.core.models.Month
import org.hanafuda.back.domain.game.koikoi.KoiKoiState
import org.hanafuda.back.domain.game.koikoi.actions.KoiKoiAction
import org.hanafuda.back.domain.game.koikoi.yaku.YakuEvaluator

fun main() {
    println("🎮 --- DÉMARRAGE DE LA PARTIE DE KOI-KOI --- 🎮\n")
    val game = KoiKoiGame("partie-test", "JoueurA", "JoueurB")
    game.start()

    val state = game.getGameState() as KoiKoiState

    fun formatCards(cards: List<Card>): String {
        if (cards.isEmpty()) return "Vide"
        return cards.joinToString(" | ") { "[${it.month} - ${it.type}]" }
    }

    println("🎴 Cartes sur la table (${state.tableCards.size}) :")
    println(formatCards(state.tableCards))

    println("\n✋ Main de ${state.player1.id} (${state.player1.hand.size}) :")
    println(formatCards(state.player1.hand))

    println("\n✋ Main de ${state.player2.id} (${state.player2.hand.size}) :")
    println(formatCards(state.player2.hand))

    println("\n👉 Phase actuelle : C'est au tour de ${state.currentTurnPlayerId}")

    println("\n=======================================================")
    println("🧪 --- TEST DU MOTEUR DE YAKU (YAKU EVALUATOR) --- 🧪")

    // On simule une fin de tour en injectant artificiellement des cartes gagnées au Joueur A
    val cartesGagnees = listOf(
        // On lui donne 4 Lumières dont la pluie (Ame-Shiko)
        Card(Month.JANUARY, CardType.HIKARI),
        Card(Month.MARCH, CardType.HIKARI),
        Card(Month.AUGUST, CardType.HIKARI),
        Card(Month.NOVEMBER, CardType.HIKARI),

        // On lui donne 11 Kasu (10 de base + 1 bonus = 2 points)
        Card(Month.JANUARY, CardType.KASU),
        Card(Month.FEBRUARY, CardType.KASU),
        Card(Month.MARCH, CardType.KASU),
        Card(Month.APRIL, CardType.KASU),
        Card(Month.MAY, CardType.KASU),
        Card(Month.JUNE, CardType.KASU),
        Card(Month.JULY, CardType.KASU),
        Card(Month.AUGUST, CardType.KASU),
        Card(Month.SEPTEMBER, CardType.KASU),
        Card(Month.OCTOBER, CardType.KASU),
        Card(Month.DECEMBER, CardType.KASU)
    )

    state.player1.collectedCards.addAll(cartesGagnees)
    println("\n🃏 Cartes collectées par ${state.player1.id} (${state.player1.collectedCards.size}) :")
    println(formatCards(state.player1.collectedCards))

    val yakus = YakuEvaluator.evaluate(state.player1.collectedCards)
    println("\n🏆 Yaku obtenus :")
    if (yakus.isEmpty()) {
        println("Aucun Yaku pour le moment.")
    } else {
        var totalPoints = 0
        yakus.forEach { (yaku, points) ->
            println(" ✔️ $yaku : $points points")
            totalPoints += points
        }
        println(" 📈 SCORE TOTAL DU TOUR : $totalPoints points")
    }

    println("\n=======================================================")
    println("🎬 --- TEST DE SIMULATION D'ACTION --- 🎬")

    // On simule le fait que le Joueur A choisisse sa première carte
    val cardToPlay = state.player1.hand.firstOrNull()
    if (cardToPlay != null) {
        println("Le joueur ${state.currentTurnPlayerId} sélectionne la carte : [${cardToPlay.month} - ${cardToPlay.type}]")

        // Instanciation de l'action selon notre nouvelle interface
        val action = KoiKoiAction.PlayCard(
            cardId = cardToPlay.id, // Assure-toi que ta classe Card possède bien un attribut 'id'
            targetTableCardId = null
        )

        println("Action construite : $action")
        println("Prêt à être envoyé à game.processAction(playerId, action) !")
    }
}