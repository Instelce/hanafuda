package org.hanafuda.back.domain.game.koikoi.yaku

import org.hanafuda.back.domain.core.models.Card
import org.hanafuda.back.domain.core.models.CardType
import org.hanafuda.back.domain.core.models.Month

object YakuEvaluator {

    /**
     * Évalue une pile de cartes collectées et retourne une Map des Yaku obtenus
     * avec leurs points calculés (incluant les bonus pour cartes supplémentaires).
     */
    fun evaluate(collectedCards: List<Card>, currentMonth: Month): Map<Yaku, Int> {
        val achievedYaku = mutableMapOf<Yaku, Int>()

        // --- 1. Évaluation des KASU (Normales) ---
        val kasuCount = collectedCards.count { it.type == CardType.KASU }
        if (kasuCount >= 10) {
            achievedYaku[Yaku.KASU] = Yaku.KASU.basePoints + (kasuCount - 10)
        }

        // --- 2. Évaluation des HIKARI (Lumières) ---
        val hikariCards = collectedCards.filter { it.type == CardType.HIKARI }
        val hikariCount = hikariCards.size
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

        // --- 3. Évaluation des TANZAKU (Rubans) ---
        val tanzakuCards = collectedCards.filter { it.type == CardType.TANZAKU }
        val tanzakuCount = tanzakuCards.size

        if (tanzakuCount >= 5) {
            achievedYaku[Yaku.TANZAKU] = Yaku.TANZAKU.basePoints + (tanzakuCount - 5)
        }

        // Akatan (3 Rubans avec poésie : Janvier, Février, Mars)
        val hasAkatan = tanzakuCards.any { it.month == Month.JANUARY } &&
                tanzakuCards.any { it.month == Month.FEBRUARY } &&
                tanzakuCards.any { it.month == Month.MARCH }
        if (hasAkatan) {
            achievedYaku[Yaku.AKATAN] = Yaku.AKATAN.basePoints + (tanzakuCount - 3)
        }

        // Aotan (3 Rubans Bleus : Juin, Septembre, Octobre)
        val hasAotan = tanzakuCards.any { it.month == Month.JUNE } &&
                tanzakuCards.any { it.month == Month.SEPTEMBER } &&
                tanzakuCards.any { it.month == Month.OCTOBER }
        if (hasAotan) {
            achievedYaku[Yaku.AOTAN] = Yaku.AOTAN.basePoints + (tanzakuCount - 3)
        }

        // --- 4. Évaluation des TANE (Animaux / Objets) ---
        val taneCards = collectedCards.filter { it.type == CardType.TANE }
        val taneCount = taneCards.size

        if (taneCount >= 5) {
            achievedYaku[Yaku.TANE] = Yaku.TANE.basePoints + (taneCount - 5)
        }

        // Ino-Shika-Cho (Sanglier: Jui, Cerf: Oct, Papillon: Juin)
        val hasInoShikaCho = taneCards.any { it.month == Month.JULY } &&
                taneCards.any { it.month == Month.OCTOBER } &&
                taneCards.any { it.month == Month.JUNE }
        if (hasInoShikaCho) {
            achievedYaku[Yaku.INO_SHIKA_CHO] = Yaku.INO_SHIKA_CHO.basePoints + (taneCount - 3)
        }

        // --- 5. Yaku Spéciaux de la Coupe de Saké ---
        // La coupe de Saké est une carte Tane du mois de Septembre
        val hasSakeCup = taneCards.any { it.month == Month.SEPTEMBER }
        if (hasSakeCup) {
            // Hanami-zake (Saké + Rideau de Mars HIKARI)
            val hasCurtain = hikariCards.any { it.month == Month.MARCH }
            if (hasCurtain) {
                achievedYaku[Yaku.HANAMI_ZAKE] = Yaku.HANAMI_ZAKE.basePoints
            }

            // Tsukimi-zake (Saké + Lune d'Août HIKARI)
            val hasMoon = hikariCards.any { it.month == Month.AUGUST }
            if (hasMoon) {
                achievedYaku[Yaku.TSUKIMI_ZAKE] = Yaku.TSUKIMI_ZAKE.basePoints
            }
        }

        val currentMonthCardsCount = collectedCards.count { it.month == currentMonth }
        if (currentMonthCardsCount == 4) {
            achievedYaku[Yaku.TSUKI_FUDA] = Yaku.TSUKI_FUDA.basePoints
        }

        return achievedYaku
    }
}