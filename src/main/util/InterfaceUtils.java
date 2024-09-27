package util;

import dto.Item;
import dto.PricingRules;

public class InterfaceUtils {
    private static final String SECTION_SEPARATOR = "-".repeat(75).concat("\n");

    public static void printSectionSeparator() {
        System.out.println(SECTION_SEPARATOR);
    }

    public static void printInvalidInputMessage(String message) {
        System.out.printf("Erroneous Input Detected: %s%n", message);
        System.out.println("Try again");
    }

    public static void printTableRow(String stringOne, String stringTwo, String stringThree) {
        System.out.format("%-15s%-29s%-20s%n", stringOne, stringTwo, stringThree);
    }

    public static void displayPricingRules(PricingRules pricingRules) {
        printTableRow("Item", "Unit Price in pence", "Special Price in pence");
        for (Item item : pricingRules.getItems()) {
            printTableRow(item.getIdentifier(), TypeUtils.getPenceDisplayPrice(item.getUnitPricePence()), TypeUtils.getSpecialPriceString(item.getSpecialPrice()));
        }
    }
}
