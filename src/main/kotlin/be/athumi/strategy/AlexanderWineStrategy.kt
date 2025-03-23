package be.athumi.strategy

import be.athumi.Wine

/**
 * For Alexander the Great's wine (never changes)
 */
class AlexanderWineStrategy : WineUpdateStrategy {

    override fun updatePrice(wine: Wine): Int = wine.price

    override fun shouldApply(wine: Wine): Boolean =
        wine.name == "Wine brewed by Alexander the Great"
}