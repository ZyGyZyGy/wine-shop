package be.athumi.strategy

import be.athumi.Wine

/**
 * For Standard wines
 */
class StandardWineStrategy : WineUpdateStrategy {

    override fun updatePrice(wine: Wine): Int {
        var newPrice = wine.price

        if (newPrice > 0) {
            newPrice -= 1

            // Double the decrease for expired wines
            if (wine.expiresInYears < 0 && newPrice > 0) {
                newPrice -= 1
            }
        }

        return maxOf(newPrice, 0)  // Ensure price never goes below 0
    }

    override fun shouldApply(wine: Wine): Boolean = true  // Default strategy
}