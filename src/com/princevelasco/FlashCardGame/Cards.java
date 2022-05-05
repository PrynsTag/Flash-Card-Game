package com.princevelasco.FlashCardGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cards {
    static Map<String, String> flashcards = new HashMap<>();
    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    /**
     * Add a flashcards to the game.
     */
    void inputCards() {
        System.out.print("Input the number of cards:\n");
        int numCards = scanner.nextInt();

        for (int i = 1; i <= numCards; i++) {
            System.out.printf("Card #%s:\n", i);
            String term = scanner.next();
            while (flashcards.containsKey(term)) {
                System.out.printf("The term \"%s\" already exists. Try again:\n", term);
                term = scanner.next();
            }

            System.out.printf("The definition for card #%s:\n", i);
            String definition = scanner.next();
            while (flashcards.containsValue(definition)) {
                System.out.printf("The definition \"%s\" already exists. Try again:\n", definition);
                definition = scanner.next();
            }

            flashcards.put(term, definition);
        }
    }

    /**
     * Ask the user to input a definition and check if it is correct for a given term.
     */
    void play() {
        for (Map.Entry<String, String> card : flashcards.entrySet()) {
            System.out.printf("Print the definition of \"%s\":\n", card.getKey());
            String answer = scanner.next();

            Question question = new Question(card.getKey(), card.getValue());
            question.isCorrect(answer);
        }
    }
}
