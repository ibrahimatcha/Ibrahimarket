package ui;

import dto.Basket;
import dto.PricingRules;
import util.TypeUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static util.TypeUtils.*;
import static util.InterfaceUtils.*;

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

            if (!isValidMenuSelection(menuSelection)) {
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
        System.out.println();
        System.out.println("Please input the item to add to the basket (Press Enter to confirm your selection)");
        displayPricingRules(basket.getPricingRules());

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
                printTableRow(product.getKey(), product.getValue().toString(), TypeUtils.getPenceDisplayPrice(productTotalPricePence));

            });
            printTableRow("Total", "", TypeUtils.getPenceDisplayPrice(basket.getGrandTotal()));
        } else {
            printInvalidInputMessage("Please select a valid item");
            addItemToBasket();
        }
    }

    private void completeTransaction() {
        printSectionSeparator();
        System.out.println("Final Basket");
        List<Map.Entry<String, Integer>> basketProducts = basket.getProducts()
                .entrySet()
                .stream()
                .filter(product -> product.getValue() > 0)
                .toList();

        if (!basketProducts.isEmpty()) {
            printTableRow("Item", "Quantity", "Price (£)");
            basketProducts.forEach(product -> {
                int productTotalPricePence = basket.getProductTotal(product.getKey());
                printTableRow(product.getKey(), product.getValue().toString(), TypeUtils.getPenceDisplayPrice(productTotalPricePence));

            });
            printTableRow("Total", "", TypeUtils.getPenceDisplayPrice(basket.getGrandTotal()));
            System.out.println();
        } else {
            System.out.println("Basket is empty.");
        }

        System.out.println("Transaction complete");
    }
}
