package dto;

import java.util.ArrayList;
import java.util.List;

public class PricingRules {
    private final List<Item> items;

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

    public Item getItem(String identifier) {
        return this.items.stream()
                .filter(item -> item.getIdentifier().equals(identifier))
                .findFirst()
                .get();
    }

    public boolean unitPricesConfigured() {
        boolean unitPricesConfigured = true;
        int currentUnitPrice;
        for (Item item: items) {
            currentUnitPrice = item.getUnitPricePence();

            if (currentUnitPrice == 0) {
                unitPricesConfigured = false;
            }
        }

        return unitPricesConfigured;
    }

    public boolean isValidItem(String input) {
        for (Item item : this.items) {
            if (item.getIdentifier().equalsIgnoreCase(input)) {
                return true;
            }
        }

        return false;
    }
}
