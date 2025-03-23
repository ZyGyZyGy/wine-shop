package be.athumi

class WineShop(var items: List<Wine>) {
    fun next() {
        // Wine Shop logic
        for (i in items.indices) {
            // Standard wines
            if (items[i].name != "Bourdeaux Conservato"
                && items[i].name != "Bourgogne Conservato"
                && !items[i].name.startsWith("Event")
            ) {
                if (items[i].price > 0 && items[i].name != "Wine brewed by Alexander the Great") {
                    items[i].price -= 1
                }
            } else {
                // Conservato or Aging wines
                if (items[i].price < 100) {
                    items[i].price += 1

                    // Event wines
                    if (items[i].name.startsWith("Event")) {
                        if (items[i].expiresInYears < 8) {
                            if (items[i].price < 100) {
                                items[i].price += 1
                            }
                        }

                        if (items[i].expiresInYears < 3) {
                            if (items[i].price < 100) {
                                items[i].price += 2
                            }
                        }
                    }
                }
            }

            // All wines except Alexander the Great
            if (items[i].name != "Wine brewed by Alexander the Great") {
                items[i].expiresInYears -= 1
            } else if (items[i].price < 0) {
                items[i].price = 0 // Ensure price never goes below 0
            }

            // Expired wines
            if (items[i].expiresInYears < 0) {
                if (!items[i].name.contains("Conservato")) {
                    if (!items[i].name.contains("Event")) {
                        if (items[i].price > 0) {
                            if (items[i].name != "Wine brewed by Alexander the Great") {
                                items[i].price -= 1 // For standard wines
                            }
                        }
                    } else {
                        // If expired, price drops to 0
                        items[i].price = 0
                    }
                // Double the increase for expired Aging (Conservato) wines
                } else {
                    if (items[i].price < 100) { // Ensure price never exceeds 100
                        items[i].price += 1
                    }
                }
            }

            if (items[i].price < 0) {
                items[i].price = 0 // Ensure price never goes below 0
            }
        }
    }
}