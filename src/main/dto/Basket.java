package dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    private final PricingRules pricingRules;
    private final Map<String, Integer> products;

    public Basket(PricingRules pricingRules) {
        this.pricingRules = pricingRules;
        this.products = new HashMap<>();
        initializeProductsMap();
    }

    public PricingRules getPricingRules() {
        return pricingRules;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void add(String identifier) {
        this.products.compute(identifier.toUpperCase(), (_, quantity) -> quantity + 1);
    }

    public int getProductTotal(String identifier) {
        Integer itemCount = this.products.get(identifier);
        Item selectedItem = this.pricingRules.getItem(identifier);
        SpecialPrice specialPrice = selectedItem.getSpecialPrice();

        if (specialPrice != null) {
            int itemsNotUnderOffer = itemCount % specialPrice.requiredUnits();
            int itemsUnderOffer = (itemCount - itemsNotUnderOffer) / specialPrice.requiredUnits();

            int itemsNotUnderOfferTotal = selectedItem.getUnitPricePence() * itemsNotUnderOffer;
            int itemsUnderOfferTotal = specialPrice.pricePence() * itemsUnderOffer;

            return itemsNotUnderOfferTotal + itemsUnderOfferTotal;
        }

        return selectedItem.getUnitPricePence() * itemCount;
    }

    public int getGrandTotal() {
        List<Integer> productTotals = new ArrayList<>();
        this.products.forEach((identifier, _) -> productTotals.add(getProductTotal(identifier)));

        return productTotals.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void initializeProductsMap() {
        for (Item item : pricingRules.getItems()) {
            this.products.put(item.getIdentifier(), 0);
        }
    }

}
