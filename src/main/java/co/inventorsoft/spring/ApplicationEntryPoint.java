package co.inventorsoft.spring;

import co.inventorsoft.spring.ui.ConsoleUI;

public class ApplicationEntryPoint {

    public static void main(String[] args) {
        final ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.run();
    }
}
