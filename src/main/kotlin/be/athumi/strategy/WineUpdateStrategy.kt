package be.athumi.strategy

import be.athumi.Wine

interface WineUpdateStrategy {
    fun updatePrice(wine: Wine): Int
    fun shouldApply(wine: Wine): Boolean
}