package org.example;

import java.util.Map;

public class InventoryService {
    Map<String, Integer> dummyMagazine = Map.of(
            "1", 30,
            "2", 25,
            "3", 3);

    public boolean isAvailable(Order order) {
        Integer itemsInStock = dummyMagazine.get(order.getItemNumber());
        return itemsInStock >= order.getQuantity();
    }
}
