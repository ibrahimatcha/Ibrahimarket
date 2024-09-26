package ui;

import dto.PricingRules;

import java.util.Scanner;

public class MainUserInterface {
    private PricingRules pricingRules;
    private MenuInterface menuInterface;

    public void launch() {
        Scanner scanner = new Scanner(System.in);
        pricingRules = new PricingRules();
        menuInterface = new MenuInterface(scanner, pricingRules);
        System.out.println("\nWelcome to the Ibrahimarket Application!\n");
        menuInterface.navigateToMenu();
    }
}
