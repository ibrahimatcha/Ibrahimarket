package util;

import dto.SpecialPrice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TypeUtils {
    public static final List<String> VALID_MENU_INPUTS = List.of("1", "2");

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

    public static boolean isValidMenuSelection(String menuSelection) {
        return menuSelection == null || !VALID_MENU_INPUTS.contains(menuSelection.trim());
    }
}
