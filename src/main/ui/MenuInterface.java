package ui;

import dto.PricingRules;

import java.util.Scanner;

import static util.GeneralUtils.*;

public class MenuInterface {
    private final Scanner scanner;
    private final PricingRules pricingRules;
    private final PricingRulesInterface pricingRulesInterface;
    private final CheckoutInterface checkoutInterface;

    public MenuInterface() {
        this.scanner = new Scanner(System.in);
        this.pricingRules = new PricingRules();
        this.pricingRulesInterface = new PricingRulesInterface(this.scanner, this.pricingRules);
        this.checkoutInterface = new CheckoutInterface(this.scanner);
    }

    public void navigateToMenu() {
        printMenuPrompt();
        System.out.println("1. New Checkout Transaction");
        System.out.println("2. Exit");
        System.out.println();
        String menuSelection = scanner.nextLine();

        if (menuSelection == null || !VALID_MENU_INPUTS.contains(menuSelection.trim())) {
            printInvalidInputMessage("Please select an option from the menu");
            navigateToMenu();
        }

        switch (menuSelection.trim()) {
            case "1" -> newCheckoutTransaction();
            case "2" -> exit();
        }
    }

    private void newCheckoutTransaction() {
        printMenuPrompt();

        if (pricingRules.unitPricesConfigured()) {
            System.out.println("Would you like to re-configure pricing rules?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println();
            String menuSelection = scanner.nextLine();

            if (menuSelection == null || !VALID_MENU_INPUTS.contains(menuSelection.trim())) {
                printInvalidInputMessage("Please select an option from the menu");
                newCheckoutTransaction();
            }

            if ("1".equals(menuSelection.trim())) {
                pricingRulesInterface.configurePricingRules("EDIT");
            }
        } else {
            pricingRulesInterface.configurePricingRules("CREATE");
        }

        pricingRulesInterface.displayPricingRules();
        checkoutInterface.configure(pricingRules);
        checkoutInterface.navigateToCheckoutMenu();

        navigateToMenu();
    }

    private void exit() {
        printSectionSeparator();
        System.out.println("Shutting down...");
        scanner.close();
        System.exit(0);
    }

    private void printMenuPrompt() {
        printSectionSeparator();
        System.out.println("Main Menu (Press Enter to confirm your selection)");
    }
}
