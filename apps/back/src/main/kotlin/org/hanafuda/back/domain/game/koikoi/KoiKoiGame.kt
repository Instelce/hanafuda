package org.hanafuda.back.domain.games.koikoi

import org.hanafuda.back.domain.core.interfaces.Game
import org.hanafuda.back.domain.core.interfaces.GameAction
import org.hanafuda.back.domain.core.models.Deck
import org.hanafuda.back.domain.game.koikoi.KoiKoiState
import org.hanafuda.back.domain.game.koikoi.PlayerState

class KoiKoiGame(
    override val id: String,
    player1Id: String,
    player2Id: String
) : Game {

    private val deck = Deck()
    private val state = KoiKoiState(
        gameId = id,
        player1 = PlayerState(id = player1Id),
        player2 = PlayerState(id = player2Id)
    )

    override val isFinished: Boolean
        get() = state.isGameOver

    override fun start() {
        deck.shuffle()
        dealCards()
    }

    private fun dealCards() {
        // Règle classique : 8 cartes par joueur, 8 cartes sur la table
        repeat(8) {
            deck.draw()?.let { state.player1.hand.add(it) }
            deck.draw()?.let { state.player2.hand.add(it) }
            deck.draw()?.let { state.tableCards.add(it) }
        }
    }

    override fun processAction(playerId: String, action: GameAction) {
        // La mécanique des tours s'insérera ici
        if (playerId != state.currentTurnPlayerId) {
            throw IllegalStateException("Ce n'est pas le tour de ce joueur.")
        }

        // TODO: Gérer le type d'action (Jouer une carte, Annoncer Koi-Koi, etc.)
    }

    override fun getGameState(): Any {
        return state
    }

}