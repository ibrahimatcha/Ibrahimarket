package ui;

import dto.Item;
import dto.PricingRules;
import dto.SpecialPrice;
import util.GeneralUtils;

import java.util.Scanner;

import static util.GeneralUtils.*;

public class PricingRulesInterface {
    private final Scanner scanner;
    private final PricingRules pricingRules;
    private static final String INTEGERS_ONLY_MESSAGE = "Integers only";

    public PricingRulesInterface(Scanner scanner, PricingRules pricingRules) {
        this.scanner = scanner;
        this.pricingRules = pricingRules;
    }

    public void configurePricingRules(String mode) {
        switch (mode) {
            case "CREATE" -> {
                createPricingRules();
                System.out.println("Thank you for configuring a unit price for each item");
            }
            case "EDIT" -> editPricingRules();
        }
        configureSpecialPricingRules();
    }

    public void displayPricingRules() {
        printTableRow("Item", "Unit Price in pence", "Special Price in pence");
        for (Item item : pricingRules.getItems()) {
            printTableRow(item.getIdentifier(), GeneralUtils.getPenceDisplayPrice(item.getUnitPricePence()), GeneralUtils.getSpecialPriceString(item.getSpecialPrice()));
        }
    }

    private void createPricingRules() {
        while (!pricingRules.unitPricesConfigured()) {
            printSectionSeparator();
            System.out.println("Unit Price has not been configured for all items.");
            System.out.println("Please input the item to set pricing rules for (Press Enter to confirm your selection)");
            displayPricingRules();

            String selectedItem = scanner.nextLine();
            if (selectedItem != null && !selectedItem.isEmpty() && pricingRules.isValidItem(selectedItem.trim())) {
                Item currentItem = pricingRules.getItems()
                        .stream()
                        .filter(item -> item.getIdentifier().equalsIgnoreCase(selectedItem.trim()))
                        .findFirst()
                        .get();

                boolean inputIsInvalid = true;
                while (inputIsInvalid) {
                    printSectionSeparator();
                    System.out.printf("Selected Item: %s%n", currentItem.getIdentifier());
                    System.out.printf("Current Price: %s%n", GeneralUtils.getPenceDisplayPrice(currentItem.getUnitPricePence()));
                    System.out.println("Please input the unit price in pence");
                    String unitPriceInput = scanner.nextLine();

                    if (GeneralUtils.isValidIntInput(unitPriceInput)) {
                        inputIsInvalid = false;
                        currentItem.setUnitPricePence(Integer.parseInt(unitPriceInput.trim()));
                        System.out.printf("%nUpdated Price for %s: %s%n", currentItem.getIdentifier(), GeneralUtils.getPenceDisplayPrice(currentItem.getUnitPricePence()));

                    } else {
                        printInvalidInputMessage(INTEGERS_ONLY_MESSAGE);
                    }
                }
            } else {
                printInvalidInputMessage("Please select a valid item");
                createPricingRules();
            }
        }
    }

    private void editPricingRules() {
        boolean finishedEditingRules = false;

        while (!finishedEditingRules) {
            printSectionSeparator();
            System.out.println("Please input the item to set pricing rules for or type 'DONE' to finish editing pricing rules (Press Enter to confirm your selection)");
            displayPricingRules();
            String input = scanner.nextLine();
            if (input != null && !input.isEmpty() && (pricingRules.isValidItem(input) || input.trim().equalsIgnoreCase("DONE"))) {
                if (input.trim().equalsIgnoreCase("DONE")) {
                    finishedEditingRules = true;
                } else {
                    Item currentItem = pricingRules.getItems()
                            .stream()
                            .filter(item -> item.getIdentifier().equalsIgnoreCase(input.trim()))
                            .findFirst()
                            .get();

                    boolean inputIsInvalid = true;
                    while (inputIsInvalid) {
                        printSectionSeparator();
                        System.out.printf("Selected Item: %s%n", currentItem.getIdentifier());
                        System.out.printf("Current Price: %s%n", GeneralUtils.getPenceDisplayPrice(currentItem.getUnitPricePence()));
                        System.out.println("Please input the unit price in pence");
                        String unitPriceInput = scanner.nextLine();

                        if (GeneralUtils.isValidIntInput(unitPriceInput)) {
                            inputIsInvalid = false;
                            currentItem.setUnitPricePence(Integer.parseInt(unitPriceInput.trim()));
                            System.out.printf("%nUpdated Price for %s: %s%n", currentItem.getIdentifier(), GeneralUtils.getPenceDisplayPrice(currentItem.getUnitPricePence()));

                        } else {
                            printInvalidInputMessage(INTEGERS_ONLY_MESSAGE);
                        }
                    }
                }
            }
        }
    }

    private void configureSpecialPricingRules() {
        printSectionSeparator();
        System.out.println("Would you like to configure special pricing rules?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println();
        String menuSelection = scanner.nextLine();

        if (menuSelection != null && VALID_MENU_INPUTS.contains(menuSelection.trim())) {
            if ("1".equals(menuSelection.trim())) {
                boolean finishedEditingRules = false;
                SpecialPrice specialPrice;

                while (!finishedEditingRules) {
                    printSectionSeparator();
                    System.out.println("Please input the item to set special pricing rules for or type 'DONE' to finish setting special pricing rules (Press Enter to confirm your selection)");
                    displayPricingRules();
                    String input = scanner.nextLine();
                    if (input != null && !input.isEmpty() && (pricingRules.isValidItem(input) || input.trim().equalsIgnoreCase("DONE"))) {
                        if (input.trim().equalsIgnoreCase("DONE")) {
                            finishedEditingRules = true;
                        } else {
                            Item currentItem = pricingRules.getItems()
                                    .stream()
                                    .filter(item -> item.getIdentifier().equalsIgnoreCase(input.trim()))
                                    .findFirst()
                                    .get();

                            boolean inputIsInvalid = true;
                            while (inputIsInvalid) {
                                printSectionSeparator();
                                System.out.printf("Selected Item: %s%n", currentItem.getIdentifier());
                                System.out.printf("Current Special Price: %s%n", GeneralUtils.getSpecialPriceString(currentItem.getSpecialPrice()));
                                System.out.println("Please input the number of units required for the offer");
                                String numberOfUnitsRequired = scanner.nextLine();
                                System.out.println("Please input the special price for the offer");
                                String specialPricePence = scanner.nextLine();

                                if (GeneralUtils.isValidIntInput(numberOfUnitsRequired) && GeneralUtils.isValidIntInput(specialPricePence)) {
                                    inputIsInvalid = false;
                                    specialPrice = new SpecialPrice(Integer.parseInt(numberOfUnitsRequired.trim()), Integer.parseInt(specialPricePence.trim()));
                                    currentItem.setSpecialPrice(specialPrice);
                                    System.out.printf("%nUpdated Special Price for %s: %s%n", currentItem.getIdentifier(), GeneralUtils.getSpecialPriceString(currentItem.getSpecialPrice()));
                                } else {
                                    printInvalidInputMessage(INTEGERS_ONLY_MESSAGE);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            printInvalidInputMessage("Please select an option from the menu");
            configureSpecialPricingRules();
        }
    }

    private void printTableRow(String stringOne, String stringTwo, String stringThree) {
        System.out.format("%-15s%-29s%-20s%n", stringOne, stringTwo, stringThree);
    }
}
