package dto;

public class SpecialPrice {
    private final String units;
    private final String price;

    public SpecialPrice(String units, String price) {
        this.units = units;
        this.price = price;
    }

    public String getUnits() {
        return units;
    }

    public String getPrice() {
        return price;
    }
}
