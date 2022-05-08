package com.princevelasco.FlashCardGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.princevelasco.FlashCardGame.utils.Map.getKeysByValue;

public class Cards {
    static Map<String, String> flashcards = new HashMap<>();
    static Map<String, Integer> cardMistakes = new HashMap<>();
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
                cardMistakes.put(parts[0], Integer.parseInt(parts[2]));
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
            writer.println("term,definition,mistakes");
            for (Map.Entry<String, String> card : flashcards.entrySet()) {
                writer.println(card.getKey() + "," + card.getValue() + "," + cardMistakes.getOrDefault(card.getKey(), 0));
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
            int mistakes = 0;

            System.out.printf("Print the definition of \"%s\":\n", term);
            String answer = scanner.next();

            String correctAnswer = flashcards.get(term);
            if (correctAnswer.equals(answer)) {
                System.out.println("Correct!");
            } else {
                cardMistakes.put(term, cardMistakes.getOrDefault(term, mistakes) + 1);

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

    void stats() {
        if (cardMistakes.isEmpty()) {
            System.out.println("There are no cards with errors.");
            return;
        }

        List<String> maxKeys = new ArrayList<>();
        int maxValue = (Collections.max(cardMistakes.values()));

        for (Map.Entry<String, Integer> entry : cardMistakes.entrySet()) {
            if (entry.getValue() == maxValue) {
                maxKeys.add(String.format("\"%s\"", entry.getKey()));
            }
        }

        if (maxKeys.size() > 1) {
            String values = String.join(", ", maxKeys);
            System.out.printf("The hardest cards are %s. You have %d errors answering them.\n", values, maxValue);
        } else if (maxKeys.size() == 1) {
            System.out.printf("The hardest card is %s. You have %s errors answering it.\n", maxKeys.get(0), maxValue);

        }

        System.out.println();
    }

    void resetStats() {
        cardMistakes.clear();
        System.out.println("Card statistics have been reset.\n");

        System.out.println();
    }

    void exit() {
        System.out.println("Bye bye!");
    }
}
