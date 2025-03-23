package be.athumi

import be.athumi.strategy.AlexanderWineStrategy
import be.athumi.strategy.AgingWineStrategy
import be.athumi.strategy.EventWineStrategy
import be.athumi.strategy.EcoWineStrategy
import be.athumi.strategy.StandardWineStrategy

class WineShop(private var items: List<Wine>) {

    private val strategies = listOf(
        AlexanderWineStrategy(),
        AgingWineStrategy(),
        EventWineStrategy(),
        EcoWineStrategy(),
        StandardWineStrategy()  // Default strategy, must be last
    )

    fun next() {
        for (wine in items) {
            // Find the first strategy that applies to this wine
            val strategy = strategies.first { it.shouldApply(wine) }

            // Update the price
            wine.price = strategy.updatePrice(wine)

            // Update expiresInYears for all wines except Alexander the Great
            if (wine.name != "Wine brewed by Alexander the Great") {
                wine.expiresInYears -= 1
            }

            // Ensure price is never negative
            if (wine.price < 0) {
                wine.price = 0
            }
        }
    }
}