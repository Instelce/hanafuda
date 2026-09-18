package org.hanafuda.back.domain.game.koikoi.yaku

enum class Yaku(val basePoints: Int) {
    GOKO(10),          // 5 Lumières
    SHIKO(8),          // 4 Lumières (sans la pluie)
    AME_SHIKO(7),      // 4 Lumières (avec la pluie)
    SANKO(5),          // 3 Lumières (sans la pluie)
    HANAMI_ZAKE(5),    // Saké + Rideau de Mars (Lumière)
    TSUKIMI_ZAKE(5),   // Saké + Lune d'Août (Lumière)
    INO_SHIKA_CHO(5),  // Sanglier (Juil), Cerf (Oct), Papillon (Juin)
    AKATAN(5),         // 3 Rubans à poésie (Rouges avec écriture)
    AOTAN(5),          // 3 Rubans bleus
    TSUKI_FUDA(4),     // Les 4 cartes du mois en cours de la partie
    TANE(1),           // 5 Graines/Animaux (+1 par carte supp)
    TANZAKU(1),        // 5 Rubans (+1 par carte supp)
    KASU(1)            // 10 Normales (+1 par carte supp)
}