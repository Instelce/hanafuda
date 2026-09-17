package org.hanafuda.back.domain.core.models

class Deck(private var cards: MutableList<Card> = createStandardDeck()) {

    fun shuffle() {
        cards.shuffle()
    }

    fun draw(): Card? {
        return if (cards.isEmpty()) null else cards.removeAt(0)
    }

    val remainingCards: Int get() = cards.size

    companion object {
        fun createStandardDeck(): MutableList<Card> {
            val deck = mutableListOf<Card>()

            // --- JANVIER (Pin) ---
            deck.add(Card(Month.JANUARY, CardType.HIKARI))
            deck.add(Card(Month.JANUARY, CardType.TANZAKU))
            deck.add(Card(Month.JANUARY, CardType.KASU))
            deck.add(Card(Month.JANUARY, CardType.KASU))

            // --- FÉVRIER (Prunier) ---
            deck.add(Card(Month.FEBRUARY, CardType.TANE))
            deck.add(Card(Month.FEBRUARY, CardType.TANZAKU))
            deck.add(Card(Month.FEBRUARY, CardType.KASU))
            deck.add(Card(Month.FEBRUARY, CardType.KASU))

            // --- MARS (Prunier) ---
            deck.add(Card(Month.MARCH, CardType.HIKARI))
            deck.add(Card(Month.MARCH, CardType.TANZAKU))
            deck.add(Card(Month.MARCH, CardType.KASU))
            deck.add(Card(Month.MARCH, CardType.KASU))

            // --- AVRIL (Prunier) ---
            deck.add(Card(Month.APRIL, CardType.TANE))
            deck.add(Card(Month.APRIL, CardType.TANZAKU))
            deck.add(Card(Month.APRIL, CardType.KASU))
            deck.add(Card(Month.APRIL, CardType.KASU))

            // --- MAI (Prunier) ---
            deck.add(Card(Month.MAY, CardType.TANE))
            deck.add(Card(Month.MAY, CardType.TANZAKU))
            deck.add(Card(Month.MAY, CardType.KASU))
            deck.add(Card(Month.MAY, CardType.KASU))

            // --- JUIN (Prunier) ---
            deck.add(Card(Month.JUNE, CardType.TANE))
            deck.add(Card(Month.JUNE, CardType.TANZAKU))
            deck.add(Card(Month.JUNE, CardType.KASU))
            deck.add(Card(Month.JUNE, CardType.KASU))

            // --- JUILLET (Prunier) ---
            deck.add(Card(Month.JULY, CardType.TANE))
            deck.add(Card(Month.JULY, CardType.TANZAKU))
            deck.add(Card(Month.JULY, CardType.KASU))
            deck.add(Card(Month.JULY, CardType.KASU))

            // --- AOUT (Prunier) ---
            deck.add(Card(Month.AUGUST, CardType.HIKARI))
            deck.add(Card(Month.AUGUST, CardType.TANZAKU))
            deck.add(Card(Month.AUGUST, CardType.KASU))
            deck.add(Card(Month.AUGUST, CardType.KASU))

            // --- SEPTEMBRE (Prunier) ---
            deck.add(Card(Month.SEPTEMBER, CardType.TANE))
            deck.add(Card(Month.SEPTEMBER, CardType.TANZAKU))
            deck.add(Card(Month.SEPTEMBER, CardType.KASU))
            deck.add(Card(Month.SEPTEMBER, CardType.KASU))

            // --- OCTOBRE (Prunier) ---
            deck.add(Card(Month.OCTOBER, CardType.TANE))
            deck.add(Card(Month.OCTOBER, CardType.TANZAKU))
            deck.add(Card(Month.OCTOBER, CardType.KASU))
            deck.add(Card(Month.OCTOBER, CardType.KASU))

            // --- FÉVRIER (Prunier) ---
            deck.add(Card(Month.NOVEMBER, CardType.HIKARI))
            deck.add(Card(Month.NOVEMBER, CardType.TANE))
            deck.add(Card(Month.NOVEMBER, CardType.TANZAKU))
            deck.add(Card(Month.NOVEMBER, CardType.KASU))

            // --- FÉVRIER (Prunier) ---
            deck.add(Card(Month.DECEMBER, CardType.HIKARI))
            deck.add(Card(Month.DECEMBER, CardType.KASU))
            deck.add(Card(Month.DECEMBER, CardType.KASU))
            deck.add(Card(Month.DECEMBER, CardType.KASU))


            return deck
        }
    }
}