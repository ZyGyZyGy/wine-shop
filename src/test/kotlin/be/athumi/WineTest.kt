package be.athumi

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WineTest {

    @Test
    fun `tasting or testing wine`() {
        assertThat(Wine("name", 0, 0)).isNotNull
    }

    @Test
    fun `a shop without wine is no shop, is it`() {
        val wines = listOf(
            Wine(name = "Standard Wine", price = 20, expiresInYears = 10),
            Wine(name = "Bourdeaux Conservato", price = 0, expiresInYears = 2),
            Wine(name = "Another Standard Wine", price = 7, expiresInYears = 5),
            Wine(name = "Wine brewed by Alexander the Great", price = 150, expiresInYears = 0),
            Wine(name = "Wine brewed by Alexander the Great", price = 80, expiresInYears = 10),
            Wine(name = "Event Wine", price = 20, expiresInYears = 15),
            Wine(name = "Event Wine", price = 49, expiresInYears = 10),
            Wine(name = "Event Wine", price = 49, expiresInYears = 5),
            Wine(name = "Eco Brilliant Wine", price = 6, expiresInYears = 3)
        )
        val shop = WineShop(wines)

        assertThat(shop).isNotNull

        shop.next()

        assertThat(shop).isNotNull
    }
}