package org.hanafuda.back.domain.game.koikoi.actions

import org.hanafuda.back.domain.core.interfaces.GameAction

sealed interface KoiKoiAction : GameAction {

    data class PlayCard(
        val cardId: String,
        val targetTableCardId: String? = null
    ) : KoiKoiAction

    data class SelectTargetForDrawnCard(
        val targetTableCardId: String
    ) : KoiKoiAction

    data class DeclareKoiKoi(
        val continueGame: Boolean
    ) : KoiKoiAction
}