import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private String menuSelection;
    private static final List<String> VALID_MENU_INPUTS = List.of("1", "2");
    private static final String SECTION_SEPARATOR = "-".repeat(30).concat("\n");

    public void launch() {
        scanner = new Scanner(System.in);
        System.out.println("\nWelcome to the Ibrahimarket Application!\n");
        navigateToMenu();
    }

    private void navigateToMenu() {
        System.out.println("Menu (Press Enter to confirm your selection)");
        System.out.println("1. New Checkout Transaction");
        System.out.println("2. Exit");
        System.out.println();
        menuSelection = scanner.nextLine();

        if (menuSelection == null || !VALID_MENU_INPUTS.contains(menuSelection)) {
            System.out.println("Erroneous Input Detected");
            System.out.println("Returning to Menu");
            System.out.println(SECTION_SEPARATOR);
            navigateToMenu();
        }

        switch (menuSelection.trim()) {
            case "1" -> newCheckoutTransaction();
            case "2" -> exit();
        }
    }

    private void newCheckoutTransaction() {
        System.out.println("Nice start lad");
        exit();
    }

    private void exit() {
        System.out.println("Shutting down...");
        scanner.close();
        System.exit(0);
    }
}
