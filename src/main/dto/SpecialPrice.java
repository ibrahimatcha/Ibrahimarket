package dto;

import util.TypeUtils;

public record SpecialPrice(int requiredUnits, int pricePence) {

    @Override
    public String toString() {
        if (requiredUnits != 0 && pricePence != 0) {
            return "%d for %s".formatted(requiredUnits, TypeUtils.getPenceDisplayPrice(pricePence));
        }

        return "N/A";
    }
}
