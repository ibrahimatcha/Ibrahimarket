package util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceUtils {

    public static String getPenceDisplayPrice(int pricePence) {
        if (pricePence ==  0) {
            return "N/A";
        } else if (pricePence < 100) {
            return "%dp".formatted(pricePence);
        } else {
            double pricePounds = pricePence / 100d;
            BigDecimal roundedPricePounds = new BigDecimal(pricePounds).setScale(2, RoundingMode.HALF_EVEN);

            return "£%s".formatted(roundedPricePounds.toString());
        }
    }
}
