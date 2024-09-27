package ui;

import dto.Basket;
import dto.Item;
import dto.PricingRules;
import util.GeneralUtils;

import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static util.GeneralUtils.*;

public class CheckoutInterface {
    private Basket basket;
    private final Scanner scanner;

    public CheckoutInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void configure(PricingRules pricingRules) {
        this.basket = new Basket(pricingRules);
    }

    public void navigateToCheckoutMenu() {
        boolean checkoutComplete = false;
        while (!checkoutComplete) {
            printSectionSeparator();
            System.out.println("1. Add item to basket");
            System.out.println("2. Complete transaction");
            System.out.println();
            String menuSelection = scanner.nextLine();

            if (menuSelection != null && VALID_MENU_INPUTS.contains(menuSelection.trim())) {
                switch (menuSelection.trim()) {
                    case "1" -> addItemToBasket();
                    case "2" -> checkoutComplete = true;
                }
            }
        }

        completeTransaction();
    }

    private void addItemToBasket() {
        printSectionSeparator();
        System.out.println("Please input the item to set pricing rules for (Press Enter to confirm your selection)");
        displayPricingRules();

        String selectedItem = scanner.nextLine();
        if (selectedItem != null && !selectedItem.isEmpty() && basket.getPricingRules().isValidItem(selectedItem.trim())) {
            basket.add(selectedItem.trim());
            printTableRow("Item", "Quantity", "Price (£)");
            Stream<Map.Entry<String, Integer>> basketProducts = basket.getProducts()
                    .entrySet()
                    .stream()
                    .filter(product -> product.getValue() > 0);

            basketProducts.forEach(product -> {
                int productTotalPricePence = basket.getProductTotal(product.getKey());
                printTableRow(product.getKey(), product.getValue().toString(), GeneralUtils.getPenceDisplayPrice(productTotalPricePence));

            });
            printTableRow("Total", "", GeneralUtils.getPenceDisplayPrice(basket.getGrandTotal()));
        } else {
            printInvalidInputMessage("Please select a valid item");
            addItemToBasket();
        }
    }

    private void completeTransaction() {
        printSectionSeparator();
        System.out.println("Final Basket");
        printTableRow("Item", "Quantity", "Price (£)");
        Stream<Map.Entry<String, Integer>> basketProducts = basket.getProducts()
                .entrySet()
                .stream()
                .filter(product -> product.getValue() > 0);

        basketProducts.forEach(product -> {
            int productTotalPricePence = basket.getProductTotal(product.getKey());
            printTableRow(product.getKey(), product.getValue().toString(), GeneralUtils.getPenceDisplayPrice(productTotalPricePence));

        });
        printTableRow("Total", "", GeneralUtils.getPenceDisplayPrice(basket.getGrandTotal()));
        System.out.println();
        System.out.println("Transaction complete");
    }

    private void printTableRow(String stringOne, String stringTwo, String stringThree) {
        System.out.format("%-15s%-29s%-20s%n", stringOne, stringTwo, stringThree);
    }

    public void displayPricingRules() {
        printTableRow("Item", "Unit Price in pence", "Special Price in pence");
        for (Item item : basket.getPricingRules().getItems()) {
            printTableRow(item.getIdentifier(), GeneralUtils.getPenceDisplayPrice(item.getUnitPricePence()), GeneralUtils.getSpecialPriceString(item.getSpecialPrice()));
        }
    }
}
