package ui;

import dto.Item;
import dto.PricingRules;
import dto.SpecialPrice;
import util.PriceUtils;

import java.util.Scanner;

import static ui.MainUserInterface.printSectionSeparator;

public class PricingRulesInterface {
    private final Scanner scanner;
    private final PricingRules pricingRules;

    public PricingRulesInterface(Scanner scanner, PricingRules pricingRules) {
        this.scanner = scanner;
        this.pricingRules = pricingRules;
    }

    public void configurePricingRules() {
        printSectionSeparator();
        System.out.println("Please input the item to set pricing rules for (Press Enter to confirm your selection)");
        printTableRow("Item", "Unit Price (pence)", "Special Price (pence)");
        for (Item item : pricingRules.getItems()) {
            printTableRow(item.getIdentifier(), PriceUtils.getPenceDisplayPrice(item.getUnitPricePence()), getSpecialPriceString(item.getSpecialPrice()));
        }

        String selectedItem = scanner.nextLine();
        if (selectedItem == null || selectedItem.isEmpty() || notAValidItem(selectedItem)) {
            System.out.println("Erroneous Input Detected");
            System.out.println("Try again");
            configurePricingRules();
        }

        Item currentItem = pricingRules.getItems()
                .stream()
                .filter(item -> item.getIdentifier().equalsIgnoreCase(selectedItem.trim()))
                .findFirst()
                .get();

        printSectionSeparator();
        System.out.printf("Selected Item: %s%n", currentItem.getIdentifier());
        System.out.println("Please input the unit price in pence");
        String unitPriceInput = scanner.nextLine();

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

    private boolean notAValidItem(String input) {
        for (Item item : pricingRules.getItems()) {
            if (item.getIdentifier().equalsIgnoreCase(input)) {
                return false;
            }
        }

        return true;
    }
}
