package ui;

public class MainUserInterface {
    private static final String SECTION_SEPARATOR = "-".repeat(50).concat("\n");

    public void launch() {
        System.out.println("\nWelcome to the Ibrahimarket Application!\n");
        new MenuInterface().navigateToMenu();
    }

    public static void printSectionSeparator() {
        System.out.println(SECTION_SEPARATOR);
    }
}
