package org.hanafuda.back.domain.core.interfaces

interface Game {
    val id: String
    val isFinished: Boolean

    fun start()
    fun processAction(playerId: String, action: GameAction)
    fun getGameState(): Any
}