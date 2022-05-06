package com.princevelasco.FlashCardGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.princevelasco.FlashCardGame.utils.Map.getKeysByValue;

public class Cards {
    static Map<String, String> flashcards = new HashMap<>();
    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    void addCard() {
        System.out.println("The card:");
        String term = scanner.next();
        if (flashcards.containsKey(term)) {
            System.out.printf("The card \"%s\" already exists.\n\n", term);
            return;
        }

        System.out.println("The definition of the card:");
        String definition = scanner.next();
        if (flashcards.containsValue(definition)) {
            System.out.printf("The definition \"%s\" already exists.\n\n", definition);
            return;
        }

        flashcards.put(term, definition);
        if (flashcards.containsKey(term) && flashcards.containsValue(definition)) {
            System.out.printf("The pair (\"%s\":\"%s\") has been added.\n", term, definition);
        }

        System.out.println();
    }

    void removeCard() {
        System.out.println("Which card?");
        String term = scanner.next();

        if (flashcards.containsKey(term)) {
            flashcards.remove(term);
            System.out.println("The card has been removed.");
        } else {
            System.out.printf("Can't remove \"%s\": there is no such card.\n", term);
        }

        System.out.println();
    }

    void importCards() {
        System.out.println("File name:");
        File file = new File(scanner.next());
        int numCards = 0;

        try (Scanner reader = new Scanner(file)) {
            reader.nextLine(); // skip header

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(",");

                flashcards.put(parts[0], parts[1]);
                numCards++;
            }
            System.out.printf("%s cards have been loaded.\n", numCards);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        System.out.println();
    }

    void exportCards() {
        System.out.println("File name:");
        File file = new File(scanner.next());
        int numCards = 0;

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("term,definition");
            for (Map.Entry<String, String> card : flashcards.entrySet()) {
                writer.println(card.getKey() + "," + card.getValue());
                numCards++;
            }

            System.out.printf("%d cards have been saved.\n", numCards);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        System.out.println();
    }

    void askCard() {
        System.out.println("How many times to ask?");
        int numToAsk = scanner.nextInt();

        List<String> terms = new ArrayList<>(flashcards.keySet());
        for (int i = 0; i < numToAsk; i++) {
            int randomIndex = (int) (Math.random() * terms.size());
            String term = terms.get(randomIndex);

            System.out.printf("Print the definition of \"%s\":\n", term);
            String answer = scanner.next();

            String correctAnswer = flashcards.get(term);
            if (correctAnswer.equals(answer)) {
                System.out.println("Correct!");
            } else {
                if (flashcards.containsValue(answer)) {
                    String otherTermCorrect = getKeysByValue(flashcards, answer);
                    System.out.printf("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".\n", correctAnswer, otherTermCorrect);
                } else {
                    System.out.printf("Wrong. The right answer is \"%s\".\n", correctAnswer);
                }
            }
        }
        System.out.println();
    }

    void exit() {
        System.out.println("Bye bye!");
    }
}
