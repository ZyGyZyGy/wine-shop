package be.athumi.strategy

import be.athumi.Wine

/**
 * For eco-brewed wines (degrade twice as fast)
 */
class EcoWineStrategy : WineUpdateStrategy {

    override fun updatePrice(wine: Wine): Int {
        var newPrice = wine.price

        // Decrease price by 2 instead of 1
        if (newPrice > 0) {
            newPrice -= 2

            // Double the decrease for expired wines (4 total)
            if (wine.expiresInYears < 0 && newPrice > 0) {
                newPrice -= 2
            }
        }

        return maxOf(newPrice, 0)  // Ensure price never goes below 0
    }

    override fun shouldApply(wine: Wine): Boolean =
        wine.name.contains("Eco")
}