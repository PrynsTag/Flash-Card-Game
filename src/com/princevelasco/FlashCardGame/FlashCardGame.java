package com.princevelasco.FlashCardGame;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class is the main class of the program. It contains the main method.
 *
 * @author Prince Carl Velasco
 * @version 1.0
 * @since 2022-02-24
 */
public class FlashCardGame {
    /**
     * This is the main runner of the program. It contains the start method.
     */
    public void start() {
        System.out.println("Welcome to the Flashcard Game!");
        System.out.println("You will be asked to enter a term and definition.");
        System.out.println("If you are finished, enter \"quit\" to exit.");
        System.out.println("If you are ready to play, enter \"play\" to begin.");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        do {
            clearScreen();

            System.out.println("Term:");
            String term = scanner.nextLine();

            System.out.println("Definition:");
            String definition = scanner.nextLine();

            System.out.println("Answer:");
            String answer = scanner.nextLine();

            Question question = new Question(term, definition);
            question.isCorrect(answer);

            System.out.println("Do you still want to continue? (Y/n)");
        } while (Objects.equals(scanner.nextLine().toLowerCase(), "y"));
    }

    /**
     * This method clears the screen.
     */
    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec(new String[]{ "clear" });
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
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
