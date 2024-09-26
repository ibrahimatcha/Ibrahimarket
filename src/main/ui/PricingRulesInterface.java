package ui;

import dto.Item;
import dto.PricingRules;
import dto.SpecialPrice;
import util.PriceUtils;

import java.util.Scanner;

public class PricingRulesInterface {
    private final Scanner scanner;
    private final PricingRules pricingRules;

    public PricingRulesInterface(Scanner scanner, PricingRules pricingRules) {
        this.scanner = scanner;
        this.pricingRules = pricingRules;
    }

    public void configurePricingRules() {
        System.out.println("Please select an item to set pricing rules for (Press Enter to confirm your selection)");
        printTableRow("Item", "Unit Price (pence)", "Special Price (pence)");

        for (Item item : pricingRules.getItems()) {
            printTableRow(item.getIdentifier(), PriceUtils.getPenceDisplayPrice(item.getUnitPricePence()), getSpecialPriceString(item.getSpecialPrice()));
        }
    }

    private String getSpecialPriceString(SpecialPrice specialPrice) {
        if (specialPrice == null) {
            return "N/A";
        }

        return specialPrice.toString();
    }

    private void printTableRow(String stringOne, String stringTwo, String stringThree) {
        System.out.format("%-15s%-29s%-20s%n", stringOne, stringTwo, stringThree);
    }
}
