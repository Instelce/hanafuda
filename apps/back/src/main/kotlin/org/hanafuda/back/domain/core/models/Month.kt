package org.hanafuda.back.domain.core.models

enum class Month {
    JANUARY,    // Pin
    FEBRUARY,   // Prunier
    MARCH,      // Cerisier
    APRIL,      // Glycine
    MAY,        // Iris
    JUNE,       // Pivoine
    JULY,       // Lespédèze
    AUGUST,     // Susuki
    SEPTEMBER,  // Chrysanthème
    OCTOBER,    // Érable
    NOVEMBER,   // Saule
    DECEMBER    // Paulownia
}

fun Month.next(): Month? {
    val allMonths = Month.entries.toTypedArray()
    val nextIndex = this.ordinal + 1
    return if (nextIndex < allMonths.size) allMonths[nextIndex] else null
}
