package dto;

public class Item {
    private final String identifier;
    private int unitPricePence;
    private SpecialPrice specialPrice;

    public Item(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getUnitPricePence() {
        return unitPricePence;
    }

    public void setUnitPricePence(int unitPricePence) {
        this.unitPricePence = unitPricePence;
    }

    public SpecialPrice getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(SpecialPrice specialPrice) {
        this.specialPrice = specialPrice;
    }
}
