package dto;

import java.util.ArrayList;
import java.util.List;

public class PricingRules {
    private List<Item> items;

    public PricingRules() {
        this.items = new ArrayList<>();
        this.items.add(new Item("A"));
        this.items.add(new Item("B"));
        this.items.add(new Item("C"));
        this.items.add(new Item("D"));
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean notConfigured() {
        int currentUnitPrice;
        for (Item item: items) {
            currentUnitPrice = item.getUnitPricePence();

            if (currentUnitPrice == 0) {
                return true;
            }
        }

        return false;
    }
}
