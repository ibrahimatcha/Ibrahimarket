package util;

import dto.SpecialPrice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class GeneralUtils {
    public static final List<String> VALID_MENU_INPUTS = List.of("1", "2");
    private static final String SECTION_SEPARATOR = "-".repeat(50).concat("\n");

    public static String getPenceDisplayPrice(int pricePence) {
        String displayString;
        if (pricePence ==  0) {
            displayString = "N/A";
        } else {
            displayString = "%dp".formatted(pricePence);
        }

        if (pricePence > 99) {
            double pricePounds = pricePence / 100d;
            BigDecimal roundedPricePounds = new BigDecimal(pricePounds).setScale(2, RoundingMode.HALF_EVEN);

            displayString += " (£%s)".formatted(roundedPricePounds.toString());
        }

        return displayString;
    }

    public static String getSpecialPriceString(SpecialPrice specialPrice) {
        if (specialPrice == null) {
            return "N/A";
        }

        return specialPrice.toString();
    }

    public static boolean isValidIntInput(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(input.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void printSectionSeparator() {
        System.out.println(SECTION_SEPARATOR);
    }

    public static void printInvalidInputMessage(String message) {
        System.out.printf("Erroneous Input Detected: %s%n", message);
        System.out.println("Try again");
    }

}
