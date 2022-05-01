package com.princevelasco.FlashCardGame;

import java.io.IOException;
import java.util.ArrayList;
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
        ArrayList<Question> flashcards = new ArrayList<>();
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

        System.out.print("Input the number of cards:\n");
        int numCards = scanner.nextInt();
        for (int i = 1; i <= numCards; i++) {
            System.out.printf("Card #%s:\n", i);
            String term = scanner.next();

            System.out.printf("The definition for card #%s:\n", i);
            String definition = scanner.next();

            Question question = new Question(term, definition);
            flashcards.add(question);
        }

        for (Question question : flashcards) {
            System.out.printf("Print the definition of \"%s\":\n", question.getTerm());
            String answer = scanner.next();

            question.isCorrect(answer);
        }
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
