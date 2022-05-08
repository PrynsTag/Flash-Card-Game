package com.princevelasco.FlashCardGame;

import java.util.Scanner;

/**
 * This class is the main class of the program. It contains the main method.
 *
 * @author Prince Carl Velasco
 * @version 1.0
 * @since 2022-02-24
 */
class FlashCardGame {

    /**
     * This is the main runner of the program. It contains the start method.
     */
    void start() {
        Scanner scanner = new Scanner(System.in);
        String action;

        Cards cards = new Cards();
        do {
            System.out.println("Input the action (add, remove, import, export, ask, exit, hardest card, reset stats):");
            action = scanner.nextLine();

            switch (action) {
                case "add" -> cards.addCard();
                case "remove" -> cards.removeCard();
                case "import" -> cards.importCards();
                case "export" -> cards.exportCards();
                case "ask" -> cards.askCard();
                case "hardest card" -> cards.stats();
                case "reset stats" -> cards.resetStats();
                case "exit" -> cards.exit();
                default -> System.out.println("Invalid action.\n");
            }
        } while (!action.equals("exit"));
    }


    /**
     * This is the string representation of the title of the program.
     *
     * @return The title of the game.
     */
    public String toString() {
        return "com.princevelasco.FlashCardGame.FlashCardGame";
    }
}
