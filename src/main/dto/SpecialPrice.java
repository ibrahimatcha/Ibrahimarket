package dto;

import util.PriceUtils;

public class SpecialPrice {
    private final int units;
    private final int pricePence;

    public SpecialPrice(int units, int pricePence) {
        this.units = units;
        this.pricePence = pricePence;
    }

    public int getUnits() {
        return units;
    }

    public int getPricePence() {
        return pricePence;
    }

    @Override
    public String toString() {
        if (units != 0 && pricePence != 0) {
            return "%d for %s".formatted(units, PriceUtils.getPenceDisplayPrice(pricePence));
        }

        return "N/A";
    }
}
