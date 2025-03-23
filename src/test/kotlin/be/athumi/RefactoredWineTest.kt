package be.athumi

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import kotlin.test.Test

class RefactoredWineTest {

    @Nested
    inner class StandardWine {

        @Test
        fun `test standard wine price decreases by 1 each year`() {
            // Arrange
            val wine = Wine("Standard Wine", 20, 10)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(19, wine.price)
            assertEquals(9, wine.expiresInYears)
        }

        @Test
        fun `test standard wine price decreases by 1 when right at expiration date`() {
            // Arrange
            val wine = Wine("Standard Wine", 20, 0)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(19, wine.price)
            assertEquals(-1, wine.expiresInYears)
        }

        @Test
        fun `test standard wine price decreases by 2 when expired`() {
            // Arrange
            val wine = Wine("Standard Wine", 10, -1)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(8, wine.price)
            assertEquals(-2, wine.expiresInYears)
        }

        @Test
        fun `test standard wine price never goes below zero`() {
            // Arrange
            val wine = Wine("Standard Wine", 1, -1)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(0, wine.price)
            assertEquals(-2, wine.expiresInYears)
        }
    }

    @Nested
    inner class EcoWine {

        @Test
        fun `test eco wine price decreases by 2 each year`() {
            // Arrange
            val wine = Wine("Eco Brilliant Wine", 10, 5)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(8, wine.price)
            assertEquals(4, wine.expiresInYears)
        }

        @Test
        fun `test eco wine price decreases by 2 when right at expiration date`() {
            // Arrange
            val wine = Wine("Eco Brilliant Wine", 10, 0)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(8, wine.price)
            assertEquals(-1, wine.expiresInYears)
        }

        @Test
        fun `test eco wine price decreases by 4 when expired`() {
            // Arrange
            val wine = Wine("Eco Brilliant Wine", 10, -1)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(6, wine.price)
            assertEquals(-2, wine.expiresInYears)
        }

        @Test
        fun `test eco wine price never goes below zero`() {
            // Arrange
            val wine = Wine("Eco Brilliant Wine", 3, -1)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(0, wine.price)
            assertEquals(-2, wine.expiresInYears)
        }
    }

    @Nested
    inner class AgingWine {

        @Test
        fun `test aging wine price increases by 1 each year`() {
            // Arrange
            val wine = Wine("Bourdeaux Conservato", 10, 5)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(11, wine.price)
            assertEquals(4, wine.expiresInYears)
        }

        @Test
        fun `test aging wine price increases by 1 when right at expiration date`() {
            // Arrange
            val wine = Wine("Bourdeaux Conservato", 10, 0)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(11, wine.price)
            assertEquals(-1, wine.expiresInYears)
        }

        @Test
        fun `test aging wine price increases by 2 when expired`() {
            // Arrange
            val wine = Wine("Bourdeaux Conservato", 10, -1)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(12, wine.price)
            assertEquals(-2, wine.expiresInYears)
        }

        @Test
        fun `test aging wine price never exceeds 100`() {
            // Arrange
            val wine = Wine("Bourdeaux Conservato", 99, -1)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(100, wine.price)
            assertEquals(-2, wine.expiresInYears)
        }
    }

    @Nested
    inner class AlexanderWine {

        @Test
        fun `test Alexander the Great's wine price never changes`() {
            // Arrange
            val wine = Wine("Wine brewed by Alexander the Great", 150, 0)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(150, wine.price)
            assertEquals(0, wine.expiresInYears) // expiration year should not change
        }
    }

    @Nested
    inner class EventWine {

        @Test
        fun `test event wine price increases over time`() {
            // Arrange
            val wine = Wine("Event Wine", 20, 15)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(21, wine.price)
            assertEquals(14, wine.expiresInYears)
        }

        @Test
        fun `test event wine price increases by 2 when less than 8 years until expiry`() {
            // Arrange
            val wine = Wine("Event Wine", 20, 7)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(22, wine.price) // +1 base, +1 for <8 years
            assertEquals(6, wine.expiresInYears)
        }

        @Test
        fun `test event wine price increases by 4 when less than 3 years until expiry`() {
            // Arrange
            val wine = Wine("Event Wine", 20, 2)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(24, wine.price) // +1 base, +1 for <8 years, +2 for <3 years
            assertEquals(1, wine.expiresInYears)
        }

        @Test
        fun `test event wine price increases by 4 when right at expiration date`() {
            // Arrange
            val wine = Wine("Event Wine", 50, 0)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(54, wine.price)
            assertEquals(-1, wine.expiresInYears)
        }

        @Test
        fun `test event wine price drops to zero when expired`() {
            // Arrange
            val wine = Wine("Event Wine", 50, -1)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(0, wine.price)
            assertEquals(-2, wine.expiresInYears)
        }

        @Test
        fun `test event wine price never exceeds 100`() {
            // Arrange
            val wine = Wine("Event Wine", 98, 2)
            val shop = WineShop(listOf(wine))

            // Act
            shop.next()

            // Assert
            assertEquals(100, wine.price) // Would be 102 but capped at 100
            assertEquals(1, wine.expiresInYears)
        }
    }

    @Test
    fun `test multiple wines are processed correctly`() {
        // Arrange
        val wines = listOf(
            Wine("Standard Wine", 20, 10),
            Wine("Bourdeaux Conservato", 0, 2),
            Wine("Another Standard Wine", 7, 5),
            Wine("Wine brewed by Alexander the Great", 150, 0),
            Wine("Wine brewed by Alexander the Great", 80, 10),
            Wine("Event Wine", 20, 15),
            Wine("Event Wine", 49, 10),
            Wine("Event Wine", 49, 5),
            Wine("Eco Brilliant Wine", 6, 3)
        )
        val shop = WineShop(wines)

        // Act
        shop.next()

        // Assert
        assertEquals(19, wines[0].price) // Standard Wine: -1
        assertEquals(9, wines[0].expiresInYears)

        assertEquals(1, wines[1].price) // Bourdeaux Conservato: +1
        assertEquals(1, wines[1].expiresInYears)

        assertEquals(6, wines[2].price) // Another Standard Wine: -1
        assertEquals(4, wines[2].expiresInYears)

        assertEquals(150, wines[3].price) // Alexander's Wine: unchanged
        assertEquals(0, wines[3].expiresInYears) // unchanged

        assertEquals(80, wines[4].price) // Alexander's Wine: unchanged
        assertEquals(10, wines[4].expiresInYears) // unchanged

        assertEquals(21, wines[5].price) // Event Wine: +1
        assertEquals(14, wines[5].expiresInYears)

        assertEquals(50, wines[6].price) // Event Wine: +1
        assertEquals(9, wines[6].expiresInYears)

        assertEquals(51, wines[7].price) // Event Wine: +1+1 (less than 8 years)
        assertEquals(4, wines[7].expiresInYears)

        assertEquals(4, wines[8].price) // Eco Wine: -2
        assertEquals(2, wines[8].expiresInYears)
    }

    @Test
    fun `test multiple runs`() {
        // Arrange
        val wines = listOf(
            Wine("Standard Wine", 20, 10),            // [0]
            Wine("Bourdeaux Conservato", 0, 2),      // [1]
            Wine("Another Standard Wine", 7, 5),      // [2]
            Wine("Wine brewed by Alexander the Great", 150, 0), // [3]
            Wine("Wine brewed by Alexander the Great", 80, 10), // [4]
            Wine("Event Wine", 20, 15),              // [5]
            Wine("Event Wine", 49, 10),              // [6]
            Wine("Event Wine", 49, 5),               // [7]
            Wine("Eco Brilliant Wine", 6, 3)         // [8]
        )
        val shop = WineShop(wines)

        // Act: Run 3 times
        for (i in 1..3) {
            shop.next()
        }

        // Assert
        assertEquals(17, wines[0].price) // Standard Wine: -3
        assertEquals(7, wines[0].expiresInYears)

        assertEquals(3, wines[1].price) // Bourdeaux Conservato: +1+1+1
        assertEquals(-1, wines[1].expiresInYears) // now expired

        assertEquals(4, wines[2].price) // Another Standard Wine: -3
        assertEquals(2, wines[2].expiresInYears)

        assertEquals(150, wines[3].price) // Alexander's Wine: unchanged
        assertEquals(0, wines[3].expiresInYears) // unchanged

        assertEquals(80, wines[4].price) // Alexander's Wine: unchanged
        assertEquals(10, wines[4].expiresInYears) // unchanged

        assertEquals(23, wines[5].price) // Event Wine: +1+1+1
        assertEquals(12, wines[5].expiresInYears)

        assertEquals(52, wines[6].price) // Event Wine: +1+1+1
        assertEquals(7, wines[6].expiresInYears) // now < 8

        assertEquals(55, wines[7].price) // Event Wine: (+1+1)+(+1+1)+(+1+1)
        assertEquals(2, wines[7].expiresInYears) // now < 3

        assertEquals(0, wines[8].price) // Eco Wine: -2-2-2=0 (floor)
        assertEquals(0, wines[8].expiresInYears)
    }
}