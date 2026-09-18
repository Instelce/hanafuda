package org.hanafuda.back.domain.game.koikoi.yaku

import org.hanafuda.back.domain.core.models.Card
import org.hanafuda.back.domain.core.models.CardType
import org.hanafuda.back.domain.core.models.Month

object YakuEvaluator {

    /**
     * Évalue une pile de cartes collectées et retourne une Map des Yaku obtenus
     * avec leurs points calculés (incluant les bonus pour cartes supplémentaires).
     */
    fun evaluate(collectedCards: List<Card>): Map<Yaku, Int> {
        val achievedYaku = mutableMapOf<Yaku, Int>()

        // 1. Évaluation des KASU (Normales)
        val kasuCount = collectedCards.count { it.type == CardType.KASU }
        if (kasuCount >= 10) {
            // 1 point de base + 1 point par carte au-delà de 10
            achievedYaku[Yaku.KASU] = Yaku.KASU.basePoints + (kasuCount - 10)
        }

        // 2. Évaluation des HIKARI (Lumières)
        val hikariCards = collectedCards.filter { it.type == CardType.HIKARI }
        val hikariCount = hikariCards.size
        // Le mois de Novembre (Pluie/Saule) a un statut spécial dans les Yaku de Lumières
        val hasRain = hikariCards.any { it.month == Month.NOVEMBER }

        when (hikariCount) {
            5 -> achievedYaku[Yaku.GOKO] = Yaku.GOKO.basePoints
            4 -> if (hasRain) {
                achievedYaku[Yaku.AME_SHIKO] = Yaku.AME_SHIKO.basePoints
            } else {
                achievedYaku[Yaku.SHIKO] = Yaku.SHIKO.basePoints
            }
            3 -> if (!hasRain) {
                achievedYaku[Yaku.SANKO] = Yaku.SANKO.basePoints
            }
        }

        // TODO: Ajouter la logique pour TANE (Animaux), TANZAKU (Rubans), INO_SHIKA_CHO, etc.

        return achievedYaku
    }
}