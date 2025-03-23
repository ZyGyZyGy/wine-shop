package be.athumi.strategy

import be.athumi.Wine

/**
 * For Event wines (Bourdeaux/Bourgogne Conservato)
 */
class EventWineStrategy : WineUpdateStrategy {

    override fun updatePrice(wine: Wine): Int {
        // If expired, price drops to 0
        if (wine.expiresInYears < 0) {
            return 0
        }

        var newPrice = wine.price

        if (newPrice < 100) {
            newPrice += 1

            // Additional increase for wines with fewer years until expiration
            if (wine.expiresInYears < 8 && newPrice < 100) {
                newPrice += 1
            }

            if (wine.expiresInYears < 3 && newPrice < 100) {
                newPrice += 2
            }
        }

        return minOf(newPrice, 100)  // Ensure price never exceeds 100
    }

    override fun shouldApply(wine: Wine): Boolean =
        wine.name.startsWith("Event")
}