package ui;

import dto.PricingRules;

import java.util.List;
import java.util.Scanner;

import static ui.MainUserInterface.printSectionSeparator;

public class MenuInterface {
    private PricingRulesInterface pricingRulesInterface;
    private String menuSelection;
    private final Scanner scanner;
    private final PricingRules pricingRules;
    private static final List<String> VALID_MENU_INPUTS = List.of("1", "2");

    public MenuInterface() {
        this.scanner = new Scanner(System.in);
        this.pricingRules = new PricingRules();
        this.pricingRulesInterface = new PricingRulesInterface(this.scanner, this.pricingRules);
    }

    public void navigateToMenu() {
        printMenuPrompt();
        System.out.println("1. New Checkout Transaction");
        System.out.println("2. Exit");
        System.out.println();
        menuSelection = scanner.nextLine();

        if (menuSelection == null || !VALID_MENU_INPUTS.contains(menuSelection)) {
            System.out.println("Erroneous Input Detected");
            System.out.println("Returning to Menu");
            navigateToMenu();
        }

        switch (menuSelection.trim()) {
            case "1" -> newCheckoutTransaction();
            case "2" -> exit();
        }
    }

    private void newCheckoutTransaction() {
        printMenuPrompt();

        if (pricingRules.notConfigured()) {
            System.out.println("Pricing rules have not been configured.");
            pricingRulesInterface.configurePricingRules();
        }

        exit();
    }

    private void exit() {
        printSectionSeparator();
        System.out.println("Shutting down...");
        scanner.close();
        System.exit(0);
    }

    private void printMenuPrompt() {
        printSectionSeparator();
        System.out.println("Menu (Press Enter to confirm your selection)");
    }
}
