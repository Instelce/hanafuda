package org.hanafuda.back.domain.game.koikoi

import org.hanafuda.back.domain.core.models.Card
import org.hanafuda.back.domain.core.models.Month

data class PlayerState(
    val id: String,
    val hand: MutableList<Card> = mutableListOf(),
    val collectedCards: MutableList<Card> = mutableListOf(),
    var points: Int = 0
)

data class KoiKoiState(
    val gameId: String,
    val player1: PlayerState,
    val player2: PlayerState,
    var month: Month,
    val tableCards: MutableList<Card> = mutableListOf(),
    var currentTurnPlayerId: String = player1.id,
    var isGameOver: Boolean = false
)