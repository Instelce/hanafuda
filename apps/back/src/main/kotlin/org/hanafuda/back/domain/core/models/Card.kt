package org.hanafuda.back.domain.core.models

data class Card(
    val month: Month,
    val type: CardType,
) {
    val id: String get() = "${month.name}_${type.name}"
}