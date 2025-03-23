package be.athumi.strategy

import be.athumi.Wine

/**
 * For Aging wines (Bourdeaux/Bourgogne Conservato)
 */
class AgingWineStrategy : WineUpdateStrategy {

    override fun updatePrice(wine: Wine): Int {
        var newPrice = wine.price

        if (newPrice < 100) {
            newPrice += 1

            // Double the increase for expired wines
            if (wine.expiresInYears < 0 && newPrice < 100) {
                newPrice += 1
            }
        }

        return minOf(newPrice, 100)  // Ensure price never exceeds 100
    }

    override fun shouldApply(wine: Wine): Boolean =
        wine.name.contains("Conservato")
}